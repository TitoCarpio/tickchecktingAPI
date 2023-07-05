package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncapas.tickcheckting.models.entities.Event;

public interface EventRepo 
	extends JpaRepository<Event, UUID>{
	 Event findByName(String name); 
	 Event findByCode(UUID code);
}
