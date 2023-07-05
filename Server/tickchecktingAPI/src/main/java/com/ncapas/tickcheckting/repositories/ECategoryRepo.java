package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.EventCategory;

public interface ECategoryRepo extends ListCrudRepository<EventCategory, UUID> {
	EventCategory findByName(String name);
	EventCategory findByCode(UUID code);
}
