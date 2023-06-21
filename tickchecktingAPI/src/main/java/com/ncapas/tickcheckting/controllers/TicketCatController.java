package com.ncapas.tickcheckting.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.repositories.TicketCategoryRepo;
import com.ncapas.tickcheckting.services.ITicketCat;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class TicketCatController {
	
	@Autowired
	ITicketCat tCatServices;
	
	
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
				return new ResponseEntity<>(new MessageDTO("TicketCategory deleted"), HttpStatus.CREATED);
			}
			else
				return new ResponseEntity<>("El identificador del elemento esta vacio",HttpStatus.INTERNAL_SERVER_ERROR);
				
				
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
