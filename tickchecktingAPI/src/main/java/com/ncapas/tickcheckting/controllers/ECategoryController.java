package com.ncapas.tickcheckting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.SaveECategoryDTO;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.services.IEventCategory;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class ECategoryController {
	@Autowired
	IEventCategory eCategoryServices;

	@Autowired
	private RequestErrorHandler errorHandler;

	// CREO UNA CATEGORIA NUEVA
	@PostMapping("eCategory")
	public ResponseEntity<?> save(@RequestBody @Valid SaveECategoryDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		try {
			EventCategory eCategory = eCategoryServices.findByName(info.getName());
			if (eCategory != null) {
				return new ResponseEntity<>(new MessageDTO("EventCategory already exists"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				eCategoryServices.save(info);
				return new ResponseEntity<>(new MessageDTO("EventCategory created"), HttpStatus.CREATED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//OBTENGO TODAS LAS CATEGORIAS
	@GetMapping("allEventCategory")
	public ResponseEntity<?> findAll() {
		List<EventCategory> eCat = eCategoryServices.findAll();

		if (!eCat.isEmpty()) {
			return new ResponseEntity<>(eCat, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
