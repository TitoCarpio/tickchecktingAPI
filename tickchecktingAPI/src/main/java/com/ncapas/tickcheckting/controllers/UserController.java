package com.ncapas.tickcheckting.controllers;

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
import com.ncapas.tickcheckting.models.dtos.SaveUserDTO;
import com.ncapas.tickcheckting.models.dtos.TokenDTO;
import com.ncapas.tickcheckting.models.dtos.UserLoginDTO;
import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class UserController {
	@Autowired
	IUser userServices;

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

	@Autowired
	private RequestErrorHandler errorHandler;

	@PostMapping("auth/singup")
	public ResponseEntity<?> saveUser(@RequestBody @Valid SaveUserDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		try {
			userServices.save(info);
			return new ResponseEntity<>(new MessageDTO("User created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("El usuario o correo ya existe"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

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

			return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
