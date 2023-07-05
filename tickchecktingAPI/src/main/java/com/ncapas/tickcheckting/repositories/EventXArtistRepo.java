package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.EventXArtist;

public interface EventXArtistRepo 
extends ListCrudRepository<EventXArtist, UUID>{
	EventXArtist findByEventCodeAndArtistCode(UUID event, UUID artist);
	
	List<EventXArtist> findByEventCode(UUID code);
	List<EventXArtist> findByArtistCode(UUID code);
}
