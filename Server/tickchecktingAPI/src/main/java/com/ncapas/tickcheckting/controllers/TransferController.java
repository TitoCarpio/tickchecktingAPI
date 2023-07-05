package com.ncapas.tickcheckting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.ConfirmTransferDTO;
import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.TransferDTO;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.Transfer;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.TransferRepo;
import com.ncapas.tickcheckting.services.ITicket;
import com.ncapas.tickcheckting.services.ITransfer;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.JWTTools;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class TransferController {

	@Autowired
	ITransfer transferServices;

	@Autowired
	IUser userServices;

	@Autowired
	ITicket ticketServices;

	@Autowired
	TransferRepo transferRepo;

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

	@Autowired
	private RequestErrorHandler errorHandler;

	@PostMapping("createTransfer")
	public ResponseEntity<?> create(@RequestBody @Valid TransferDTO info, BindingResult validations,
			HttpServletRequest request) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		// obtengo el toquen de los headers de la peticion
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);

		// busco el usuario
		User sender = userServices.findOneByUsernameOrEmail(username, username);

		// busco el usuario que va a recibir el ticket
		User reciver = userServices.findOneByUsernameOrEmail(info.getEmailReciver(), info.getEmailReciver());

		Ticket ticket = ticketServices.findByCode(info.getCodeTicket());

		Transfer transfer = transferRepo.findBySenderCodeAndTicketCode(sender.getCode(), ticket.getCode());

		if (transfer == null) {
			if (sender != null && reciver != null && ticket != null) {
				try {
					transferServices.save(ticket, sender, reciver);
					Transfer transferB = transferRepo.findBySenderCodeAndTicketCode(sender.getCode(), ticket.getCode());
					return new ResponseEntity<>(transferB, HttpStatus.CREATED);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (!transfer.getTicket().getCode().equals(ticket.getCode()) && sender != null && reciver != null
				&& ticket != null) {
			try {
				transferServices.save(ticket, sender, reciver);
				return new ResponseEntity<>(new MessageDTO("Transfer created"), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		return new ResponseEntity<>("The ticket is already being transferred", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("confirmTransfer")
	public ResponseEntity<?> confirm(@RequestBody @Valid ConfirmTransferDTO info, BindingResult validations){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		//verifico si existe la transferencia
		Transfer transfer = transferServices.findByCode(info.getCodeTransfer());
		
		if (transfer != null) {
			if (transfer.getHastemp().equals(info.getHash())) {
				transferServices.confirmTransfer(transfer);
				return new ResponseEntity<>("Successful transfer",HttpStatus.OK);
			}else
				return new ResponseEntity<>("The confirmation code does not match",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Transfer not found",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
