package com.ncapas.tickcheckting.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.services.IPurchase;
import com.ncapas.tickcheckting.services.ITicket;
import com.ncapas.tickcheckting.services.ITicketCat;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class PurchaseController {

	@Autowired
	IPurchase purchaseServices;
	
	@Autowired
	IUser userServices;
	
	@Autowired
	ITicketCat tCatSrvices;
	
	@Autowired
	ITicket ticketServices;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@PostMapping("savePurchase")
	public ResponseEntity<?> save(@RequestBody @Valid PurchaseDTO info, BindingResult validations ){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		//obtengo el usuario
		User user = userServices.findOneByUsernameOrEmail(info.getUsername(), info.getUsername());
		
		//verifico si no existe una compra ya con ese usuario
//		Purchase purchase = purchaseServices.findByUserCode(user);
		
		//busco la categoria
		TicketCategory categoria = tCatSrvices.findByCode(UUID.fromString(info.getTicketCatCode()));
		
//		if (purchase == null) {
			try {
				purchaseServices.save(info, user, categoria);
				return new ResponseEntity<>(new MessageDTO("Tickets created"), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
//		}else {
//			try {
//				ticketServices.save(info, user, categoria, purchase);
//				return new ResponseEntity<>(new MessageDTO("Tickets created"), HttpStatus.CREATED);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//		}

	}
}
