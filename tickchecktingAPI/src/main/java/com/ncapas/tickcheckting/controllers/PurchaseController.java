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
import com.ncapas.tickcheckting.models.dtos.UpdateTicketCatDTO;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.services.IPurchase;
import com.ncapas.tickcheckting.services.ITicket;
import com.ncapas.tickcheckting.services.ITicketCat;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
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

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

	@Autowired
	private RequestErrorHandler errorHandler;

	@PostMapping("savePurchase")
	public ResponseEntity<?> save(@RequestBody @Valid PurchaseDTO info, BindingResult validations,
			HttpServletRequest request) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		// obtengo el toquen de los headers de la peticion
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);
		// obtengo el usuario
		User user = userServices.findOneByUsernameOrEmail(username, username);

		// verifico si no existe una compra ya con ese usuario
//		Purchase purchase = purchaseServices.findByUserCode(user);

		// busco la categoria
		TicketCategory categoria = tCatSrvices.findByCode(UUID.fromString(info.getTicketCatCode()));
		
		//verifico que la cantidad de ticketCat alcance para la compra que se quiere realizar
		if ( info.getCantidad() <= categoria.getQty()) {
			try {
				
				purchaseServices.save(info, user, categoria);
				
				//actualizo la cantidad de tickets dispobibles
				UpdateTicketCatDTO update = new UpdateTicketCatDTO(
						categoria.getCode(),
						categoria.getName(), 
						categoria.getPrice(),
						categoria.getQty()-info.getCantidad());
				
				tCatSrvices.update(update);
				return new ResponseEntity<>(new MessageDTO("Tickets created"), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return new ResponseEntity<>("The quantity exceeds the existing ones",HttpStatus.INTERNAL_SERVER_ERROR);


	}
}
