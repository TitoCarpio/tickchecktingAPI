package com.ncapas.tickcheckting.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.PermisionDTO;
import com.ncapas.tickcheckting.models.dtos.ResUserLoginDTO;
import com.ncapas.tickcheckting.models.dtos.SaveUserDTO;
import com.ncapas.tickcheckting.models.dtos.TokenDTO;
import com.ncapas.tickcheckting.models.dtos.UserLoginDTO;
import com.ncapas.tickcheckting.models.entities.Permision;
import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.models.entities.UserXPermision;
import com.ncapas.tickcheckting.services.IPermision;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.services.IUserXPermision;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
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
			Permision permision = permisionServices.findByName("user");
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

	// login del usuario
	@PostMapping("login")
	public ResponseEntity<?> login(@ModelAttribute @Valid UserLoginDTO info, BindingResult validations,
			HttpServletRequest request) {
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
					new ResUserLoginDTO(new TokenDTO(token).getToken(), user.getUsername(), user.getEmail(), permision),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//CAMBIO EL ESTADO DEL USUARIO A ACTIVO
	@PostMapping("active")
	public ResponseEntity<?> active(HttpServletRequest request) {
		// obtengo el toquen de los headers de la peticion
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);
		try {

			userServices.activeUser(username);
			return new ResponseEntity<>("Se cambio el estado de activo del usuario", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

}
