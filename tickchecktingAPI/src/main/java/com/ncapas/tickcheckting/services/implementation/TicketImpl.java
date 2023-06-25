package com.ncapas.tickcheckting.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.TicketRepo;
import com.ncapas.tickcheckting.services.ITicket;

import jakarta.transaction.Transactional;

@Service
public class TicketImpl implements ITicket {

	@Autowired
	TicketRepo ticketRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(PurchaseDTO info, User user, TicketCategory ticketCat, Purchase purchase) throws Exception {
		Ticket ticket = new Ticket(false, new Date(), ticketCat, purchase);
		ticketRepo.save(ticket);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(String code) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Ticket findByCode(UUID code) {
		Ticket ticket = ticketRepo.findByCode(code);
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> findTicketByUser(List<Purchase> purchases) {
		List<Ticket> tickets =  new ArrayList<>();
		//recorro toda las compras para buscar los tickets
		for(Purchase purchase : purchases) {
			//agrego todos los elementos a la lista de tickets
			tickets.addAll(ticketRepo.findByPurchaseCode(purchase.getCode()));
		}
		
		return tickets;
	}

	@Override
	public void changeActive(Ticket ticket) {
		ticket.setActive(true);
		ticketRepo.save(ticket);
		
	}

	@Override
	public void changeDesactive(Ticket ticket) {
		ticket.setActive(false);
		ticketRepo.save(ticket);
	}
	
	
	
	
	
	

}
