package com.ncapas.tickcheckting.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.SaveTicketCatDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateTicketCatDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.services.ITicketCat;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class TicketCatController {
	
	@Autowired
	ITicketCat tCatServices;
	
	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	private RequestErrorHandler errorHandler;

	
	@DeleteMapping("delTicketCat/{code}")
	public ResponseEntity<?> deleteTCat(@PathVariable UUID code){
		try {
			if (code != null) {
				TicketCategory buscado = tCatServices.findByCode(code);
				if (buscado == null) {
					return new ResponseEntity<>("field not found",HttpStatus.INTERNAL_SERVER_ERROR);
				}
				tCatServices.delete(code);
				return new ResponseEntity<>(new MessageDTO("TicketCategory deleted"), HttpStatus.OK);
			}
			else
				return new ResponseEntity<>("El identificador del elemento esta vacio",HttpStatus.INTERNAL_SERVER_ERROR);
				
				
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("updateTicketCat")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateTicketCatDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		try {
			tCatServices.update(info);
			return new ResponseEntity<>(new MessageDTO("TicketCategory Updated"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("ticketCatByEvent/{code}")
	public ResponseEntity<?> findByEvent(@PathVariable UUID code){
		if (!code.equals(null)) {
			List<TicketCategory> ticketCat = tCatServices.findByEventCode(code);
			return new ResponseEntity<>(ticketCat, HttpStatus.OK);
		}else
			return new ResponseEntity<>("No se encontraron datos",HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@PostMapping("saveTickCat/{code}")
	public ResponseEntity<?> save(@PathVariable UUID code, @RequestBody @Valid SaveTicketCatDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		if (!code.equals(null)) {
			//Busco el evento
			Event evento = eventRepo.findByCode(code);
			if (evento != null) {
				try {
					tCatServices.save(info, evento);
					return new ResponseEntity<>(new MessageDTO("TicketCategory created"), HttpStatus.CREATED);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			return new ResponseEntity<>("Event not found",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>("Event not found",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
