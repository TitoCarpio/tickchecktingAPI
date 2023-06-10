package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.EventXArtist;

public interface EventXArtistRepo 
	extends ListCrudRepository<EventXArtist, UUID>{

}
