package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.EventXSponsor;

public interface EventXSponsorRepo 
extends ListCrudRepository<EventXSponsor, UUID>{
	EventXSponsor findByEventCodeAndSponsorCode(UUID event, UUID sponsor);
	List<EventXSponsor> findByEventCode(UUID code);
}
