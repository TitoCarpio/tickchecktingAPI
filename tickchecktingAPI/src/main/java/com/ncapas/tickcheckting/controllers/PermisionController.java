package com.ncapas.tickcheckting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.SavePermisionDTO;
import com.ncapas.tickcheckting.models.entities.Permision;
import com.ncapas.tickcheckting.services.IPermision;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class PermisionController {

	@Autowired IPermision permisionServices;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@PostMapping("permision")
	public ResponseEntity<?> save(@ModelAttribute @Valid SavePermisionDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			permisionServices.save(info);
			return new ResponseEntity<>(new MessageDTO("Permision created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Permission already exists"),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("all/permision")
	public ResponseEntity<?> findAll(){
		try {
			List<Permision> permisions = permisionServices.findAll();
			return new ResponseEntity<>(permisions, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
