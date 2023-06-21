package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Event;

public interface EventRepo 
	extends ListCrudRepository<Event, UUID>{
	 Event findByName(String name); 
}
