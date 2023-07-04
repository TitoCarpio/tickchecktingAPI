package com.ncapas.tickcheckting.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.ResTicketDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.Attend;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.services.IAttend;
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
	IAttend attendServices;

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
			// busco si el ticket no a sido canjeado
			Attend attend = attendServices.findByTicketCode(ticket.getCode());

			if (attend == null) {
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
		}
		return resTicket;
	}
	
	//funcion que le da formato a los tickets ya canjeados
	private List<ResTicketDTO> formatAttend(List<Ticket> tickets) {
		List<ResTicketDTO> resTicket = new ArrayList<>();

		for (Ticket ticket : tickets) {
			// busco si el ticket no a sido canjeado
			Attend attend = attendServices.findByTicketCode(ticket.getCode());

			if (attend != null) {
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
		}
		return resTicket;
	}

	// TODO: agregar la paginacion a los ticketes mediante la paginacion de las compras
	
	
	@GetMapping("ticketByUser")
	public ResponseEntity<?> findTickets(HttpServletRequest request, @RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "16") int size) {
		// obtengo el nombre del usuario
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);

		if (username != null) {
			// busco el usuario
			User user = userServices.findOneByUsernameOrEmail(username, username);
			// busco todas las compras de ese usuario
			Page<Purchase> purchase = purchaseServices.findPurchaseByUser(user, page, size);

//			List<Ticket> tickets = ticketServices.findTicketByUser(purchase.getContent());
			List<Ticket> tickets = ticketServices.findTicketByUser(purchase.getContent());
			List<ResTicketDTO> resTicket = format(tickets);
			
//			PageDTO<ResTicketDTO> response = new PageDTO<>(
//					resTicket,
//					purchase.getNumber(),
//					purchase.getSize(),
//					purchase.getTotalElements(),
//					purchase.getTotalPages()
//					
//					);
			
			
			
			return new ResponseEntity<>(resTicket, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("activeTicket/{code}")
	public ResponseEntity<?> changeActive(@PathVariable UUID code) {
		if (!code.equals(null)) {
			Ticket ticket = ticketServices.findByCode(code);
			if (ticket != null) {
				ticketServices.changeActive(ticket);
				return new ResponseEntity<>("Ticket activated", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Ticket not found", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Ticket not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("desactiveTicket/{code}")
	public ResponseEntity<?> changeDesactive(@PathVariable UUID code) {
		if (!code.equals(null)) {
			Ticket ticket = ticketServices.findByCode(code);
			if (ticket != null) {
				ticketServices.changeDesactive(ticket);
				return new ResponseEntity<>("Ticket Desactivated", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Ticket not found", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>("Ticket not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("attendTicket")
	public ResponseEntity<?> attendTickets(HttpServletRequest request, @RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "16") int size) {
		// obtengo el nombre del usuario
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		// obtengo el user del token
		String username = jwtTools.getUsernameFrom(token);
		
		if (username != null) {
			// busco el usuario
			User user = userServices.findOneByUsernameOrEmail(username, username);
			// busco todas las compras de ese usuario
			Page<Purchase> purchase = purchaseServices.findPurchaseByUser(user, page, size);

//			List<Ticket> tickets = ticketServices.findTicketByUser(purchase.getContent());
			List<Ticket> tickets = ticketServices.findTicketByUser(purchase.getContent());
			List<ResTicketDTO> resTicket = formatAttend(tickets);
			return new ResponseEntity<>(resTicket, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);

		
	}

}
