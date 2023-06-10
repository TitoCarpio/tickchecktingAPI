package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Ticket;

public interface TicketRepo 
extends ListCrudRepository<Ticket, UUID>{

}
