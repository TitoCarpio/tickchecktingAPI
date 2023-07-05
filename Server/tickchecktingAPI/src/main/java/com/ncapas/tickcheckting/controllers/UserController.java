package com.ncapas.tickcheckting.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.ActiveUserDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.PageDTO;
import com.ncapas.tickcheckting.models.dtos.PermisionDTO;
import com.ncapas.tickcheckting.models.dtos.ResAllUserDTO;
import com.ncapas.tickcheckting.models.dtos.ResUserLoginDTO;
import com.ncapas.tickcheckting.models.dtos.SaveUserDTO;
import com.ncapas.tickcheckting.models.dtos.TokenDTO;
import com.ncapas.tickcheckting.models.dtos.UserLoginDTO;
import com.ncapas.tickcheckting.models.dtos.UserLoginGDTO;
import com.ncapas.tickcheckting.models.entities.Permision;
import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.models.entities.UserXPermision;
import com.ncapas.tickcheckting.repositories.UserRepo;
import com.ncapas.tickcheckting.services.IPermision;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.services.IUserXPermision;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class UserController {
	@Autowired
	IUser userServices;

	@Autowired
	IPermision permisionServices;

	@Autowired
	IUserXPermision uPermsionServices;
	
	@Autowired
	UserRepo userRepo;

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

	@Autowired
	private RequestErrorHandler errorHandler;

	// CREO UN NUEVO USUARIO CON OS PERMISOS DE USUERIO, PERO ESTADO INACTIVO
	@PostMapping("auth/singup")
	public ResponseEntity<?> saveUser(@RequestBody @Valid SaveUserDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		try {
			userServices.save(info);
			User user = userServices.findOneByUsernameOrEmail(info.getUsername(), info.getEmail());
			Permision permision = permisionServices.findByName("User");
			if (user != null && permision != null) {
				UserXPermision uPermison = new UserXPermision(new Date(), user, permision);
				uPermsionServices.save(uPermison);
			}
			return new ResponseEntity<>(new MessageDTO("User created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("El usuario o correo ya existe"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	// funcion para obtener los permisos
	public static List<PermisionDTO> recorrerLista(List<UserXPermision> permision) {
		List<PermisionDTO> nuevo = new ArrayList<>();
		for (UserXPermision permi : permision) {
			PermisionDTO name = new PermisionDTO();
			name.setPermision(permi.getPermision().getName());
			nuevo.add(name);

		}
		return nuevo;
	}

	// login del usuario normal
	@PostMapping("login")
	public ResponseEntity<?> login(@ModelAttribute @Valid UserLoginDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userServices.findOneByUsernameOrEmail(info.getIdentifier(), info.getIdentifier());

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (!userServices.comparePassword(info.getPassword(), user.getPassword())) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {

			Token token = userServices.registerToken(user);
			List<UserXPermision> uPermision = uPermsionServices.findUser(user.getCode());
			List<PermisionDTO> permision = recorrerLista(uPermision);

			return new ResponseEntity<>(
					new ResUserLoginDTO(new TokenDTO(token).getToken(), user.getUsername(), user.getActive() ,user.getEmail(), permision),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//login del usuario con Google
	@PostMapping("loginGoogle")
	public ResponseEntity<?> loginGoogle(@RequestBody @Valid UserLoginGDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userServices.findOneByUsernameOrEmail(info.getUsername(), info.getEmail());
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {

			Token token = userServices.registerToken(user);
			List<UserXPermision> uPermision = uPermsionServices.findUser(user.getCode());
			List<PermisionDTO> permision = recorrerLista(uPermision);

			return new ResponseEntity<>(
					new ResUserLoginDTO(new TokenDTO(token).getToken(), user.getUsername(), user.getActive() ,user.getEmail(), permision),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//CAMBIO EL ESTADO DEL USUARIO A ACTIVO
	@PutMapping("active")
	public ResponseEntity<?> active(@RequestBody @Valid ActiveUserDTO info,  BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepo.findOneByUsernameOrEmail(info.getUsername(), info.getUsername());
		
		if (user != null) {
			try {
				userServices.activeUser(user);
				return new ResponseEntity<>("User activated", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("User not found",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("User not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//funcion que me da formato de respuesta de la peticion de obtener todos los usuarios
	public  List<ResAllUserDTO> responseAll(List<User> users){
		List<ResAllUserDTO> nuevo = new ArrayList<>();
		
		for(User user : users) {
			List<UserXPermision> uPermision = uPermsionServices.findUser(user.getCode());
			List<PermisionDTO> permision = recorrerLista(uPermision);
			ResAllUserDTO info = new ResAllUserDTO(user.getUsername(), user.getActive(), user.getEmail(), permision);
			nuevo.add(info);
		}
		
		return nuevo;
	}
	
	
	
	
	//obtener todos los usuarios
	@GetMapping("allUsers")
	public ResponseEntity<?> alluser(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") int size){
		
		Page<User> users = userServices.findAll(page, size);
		
		List<ResAllUserDTO> userTransform = responseAll(users.getContent());
		
		PageDTO<ResAllUserDTO> response = new PageDTO<>(
				userTransform,
				users.getNumber(),
				users.getSize(),
				users.getTotalElements(),
				users.getTotalPages()
				);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
