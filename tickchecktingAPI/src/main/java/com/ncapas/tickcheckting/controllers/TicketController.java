package com.ncapas.tickcheckting.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.ResTicketDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.services.IPurchase;
import com.ncapas.tickcheckting.services.ITicket;
import com.ncapas.tickcheckting.services.IUser;
import com.ncapas.tickcheckting.utils.JWTTools;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class TicketController {

	@Autowired
	ITicket ticketServices;

	@Autowired
	IPurchase purchaseServices;

	@Autowired
	IUser userServices;

	@Autowired
	ArtistRepo artistRepo;

	// para obtener el usuario
	@Autowired
	JWTTools jwtTools;

//	@Autowired
//	private RequestErrorHandler errorHandler;

	// funcion que me recorre la lista de EventXArtist y me da una lista de nombre
	// de artistas
	private List<String> artist(List<EventXArtist> artistas) {
		List<String> nuevo = new ArrayList<>();

		for (EventXArtist artist : artistas) {
			Artist artistB = artist.getArtist();
			nuevo.add(artistB.getName());
		}
		return nuevo;
	}

	// funcion que le da formato a la lista de tickets
	private List<ResTicketDTO> format(List<Ticket> tickets) {
		List<ResTicketDTO> resTicket = new ArrayList<>();

		for (Ticket ticket : tickets) {
			// obtengo la lista de artistas
			List<EventXArtist> artistas = ticket.getCategory_id().getEvent_id().getEventArtist();
			List<String> artist = artist(artistas);
			;
			ResTicketDTO nuevo = new ResTicketDTO(ticket.getCode(), ticket.getActive(),
					ticket.getCategory_id().getCode(), ticket.getCategory_id().getName(),
					ticket.getCategory_id().getEvent_id().getName(), artist,
					ticket.getCategory_id().getEvent_id().getEvent_date(),
					ticket.getCategory_id().getEvent_id().getEventHour());
			resTicket.add(nuevo);
		}
		return resTicket;
	}

	@GetMapping("ticketByUser")
	public ResponseEntity<?> findTickets(HttpServletRequest request) {
		// obtengo el nombre del usuario
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);

		if (username != null) {
			// busco el usuario
			User user = userServices.findOneByUsernameOrEmail(username, username);
			// busco todas las compras de ese usuario
			List<Purchase> purchase = purchaseServices.findPurchaseByUser(user);

			List<Ticket> tickets = ticketServices.findTicketByUser(purchase);
			List<ResTicketDTO> resTicket = format(tickets);
			return new ResponseEntity<>(resTicket, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
