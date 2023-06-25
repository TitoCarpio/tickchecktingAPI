package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Ticket;

public interface TicketRepo 
extends ListCrudRepository<Ticket, UUID>{
	List<Ticket> findByPurchaseCode(UUID purchase);
	Ticket findByCode(UUID code);

}
