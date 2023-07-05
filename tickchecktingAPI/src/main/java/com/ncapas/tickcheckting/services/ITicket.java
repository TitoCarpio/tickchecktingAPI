package com.ncapas.tickcheckting.services;

import java.util.List;
import java.util.UUID;

import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;

public interface ITicket {
	void save(PurchaseDTO info, User user, TicketCategory ticketCat, Purchase purchase) throws Exception;
	void deleteByCode(String code) throws Exception;
	Ticket findByCode(UUID code);
	List<Ticket> findAll();
	void changeActive(Ticket ticket);
	void changeDesactive(Ticket ticket);
	
	//Obtener los tikets de un usuario
	List<Ticket> findTicketByUser(List<Purchase> purchase);
}
