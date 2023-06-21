package com.ncapas.tickcheckting.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.DeletePlaceDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.SavePlaceDTO;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.repositories.PlaceRepo;
import com.ncapas.tickcheckting.services.IPlace;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class PlaceController {
	
	@Autowired
	IPlace placeServices;
	
	@Autowired
	PlaceRepo placeRepository;
	
	@Autowired
	private RequestErrorHandler errorHandler;

	@PostMapping("savePlace")
	public ResponseEntity<?> save(@RequestBody @Valid SavePlaceDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			Place place = placeRepository.findByName(info.getName());
			//verifico si existe el lugar
			if (place == null) {
				placeServices.save(info);
				return new ResponseEntity<>(new MessageDTO("Place created"), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("The place already exists",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("deletePlace")
	public ResponseEntity<?> delete(@RequestBody @Valid DeletePlaceDTO info,  BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			//primero busco si existe el lugar
			Place place = placeRepository.findByCode(UUID.fromString(info.getPlaceCode()));
			if (place == null) {
				return new ResponseEntity<>("The place was not found",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			placeServices.delete(info);
			return new ResponseEntity<>(new MessageDTO("Place deleted"), HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("allPlace")
	public ResponseEntity<?> allPlace(){
		List<Place> places = placeServices.findAll();
		return new ResponseEntity<>(places, HttpStatus.OK);
	}

}
