package com.ncapas.tickcheckting.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.AddPermisionDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.SavePermisionDTO;
import com.ncapas.tickcheckting.models.entities.Permision;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.models.entities.UserXPermision;
import com.ncapas.tickcheckting.repositories.UserXPermisionRepo;
import com.ncapas.tickcheckting.services.IPermision;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.services.IUserXPermision;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class PermisionController {

	@Autowired
	IPermision permisionServices;

	@Autowired
	IUser userServices;

	@Autowired
	IUserXPermision uPermisionServices;

	@Autowired
	UserXPermisionRepo uPermsionRepo;

	@Autowired
	private RequestErrorHandler errorHandler;

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

	// CREO UN NUEVO PERMISO
	@PostMapping("permision")
	public ResponseEntity<?> save(@ModelAttribute @Valid SavePermisionDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		try {
			permisionServices.save(info);
			return new ResponseEntity<>(new MessageDTO("Permision created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Permission already exists"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// OBTENGO TODOS LOS PERMISOS
	@GetMapping("all/permision")
	public ResponseEntity<?> findAll() {
		try {
			List<Permision> permisions = permisionServices.findAll();
			return new ResponseEntity<>(permisions, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// AGREGA O QUITA PERMISOS AL USUARIO
	@PostMapping("addremovePermision")
	public ResponseEntity<?> addPermisiontoUser(@ModelAttribute @Valid AddPermisionDTO info,BindingResult validations,HttpServletRequest request ) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			// obtengo el toquen de los headers de la peticion
			String tokenHeader = request.getHeader("Authorization");
			String token = tokenHeader.substring(7);
			// obtengo el user del token
			String username = jwtTools.getUsernameFrom(token);
			
			Permision permision = permisionServices.findByCode(UUID.fromString(info.getPermision()));
			
			User user = userServices.findOneByUsernameOrEmail(username, username);
			if (user != null && permision !=null ) {
				//verifico que el usuario no tenga ya ese permiso
				UserXPermision uPermision = uPermsionRepo.findByUserCodeAndPermisionCode(user.getCode(), permision.getCode());
				if(uPermision == null) {
					UserXPermision uPermison = new UserXPermision(new Date(), user, permision);
					uPermsionRepo.save(uPermison);
					return new ResponseEntity<>(new MessageDTO("Permision added to user"), HttpStatus.OK);
				}else {
					uPermsionRepo.delete(uPermision);
					return new ResponseEntity<>(new MessageDTO("User permission removed"), HttpStatus.OK);
				}
				
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
		
	

}
