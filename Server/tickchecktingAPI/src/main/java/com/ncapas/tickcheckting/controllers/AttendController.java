package com.ncapas.tickcheckting.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.entities.Attend;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.services.IAttend;
import com.ncapas.tickcheckting.services.ITicket;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class AttendController {
	
	@Autowired
	IAttend attendServices;
	
	@Autowired
	ITicket ticketServices;
	
	@PostMapping("newAttend/{code}")
	public ResponseEntity<?> save( @PathVariable UUID code){
		if (!code.equals(null)) {
			//busco el ticket
			Ticket ticket = ticketServices.findByCode(code);
			
			//busco que ese ticket no este en los canjeados
			Attend attend = attendServices.findByTicketCode(code);
			
			if (ticket != null && ticket.getActive() && attend == null) {
				try {
					attendServices.save(ticket);
					return new ResponseEntity<>(new MessageDTO("Attend created"), HttpStatus.CREATED);
					
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else
				return new ResponseEntity<>("Ticket not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
