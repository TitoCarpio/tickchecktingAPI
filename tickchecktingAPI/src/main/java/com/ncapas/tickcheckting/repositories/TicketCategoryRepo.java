package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Ticket_Category;

public interface TicketCategoryRepo 
extends ListCrudRepository<Ticket_Category, UUID>{

}
