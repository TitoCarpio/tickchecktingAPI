package com.ncapas.tickcheckting.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.DeleteSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.NewSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateSponsorDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.repositories.EventXSponsorRepo;
import com.ncapas.tickcheckting.services.ISponsor;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class SponsorController {
	@Autowired
	ISponsor sponsorServices;

	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	EventXSponsorRepo eSponsorRepo;

	@Autowired
	private RequestErrorHandler errorHandler;

	@PutMapping("updateSponsor")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateSponsorDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		Sponsor sponsor = sponsorServices.findByCode(UUID.fromString(info.getCode()));

		if (sponsor != null) {
			sponsorServices.update(info, sponsor);
			return new ResponseEntity<>(new MessageDTO("Sponsor updated"), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("createSponsor")
	public ResponseEntity<?> save(@RequestBody @Valid NewSponsorDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		// busco el evento
		Event evento = eventRepo.findByCode(UUID.fromString(info.getCodeEvent()));

		if (evento != null) {
			try {
				sponsorServices.newSponsor(info, evento);
				return new ResponseEntity<>("Sponsor created",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
			return new ResponseEntity<>("Event not found",HttpStatus.INTERNAL_SERVER_ERROR);
		
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("deleteSponsor")
	public ResponseEntity<?> delete(@RequestBody @Valid DeleteSponsorDTO info, BindingResult validations ){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		//busco el elemento de la tabla cruz
		EventXSponsor eSponsor =  eSponsorRepo.findByEventCodeAndSponsorCode(UUID.fromString(info.getCodeEvent()), UUID.fromString(info.getCodeSponsor()));
		
		if (eSponsor != null) {
			try {
				sponsorServices.delete(eSponsor);
				return new ResponseEntity<>(new MessageDTO("Sponsor deleted"), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
