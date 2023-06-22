package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Artist;

public interface ArtistRepo 
	extends ListCrudRepository<Artist, UUID>{
	Artist findByName(String name);
	Artist findByCode(UUID code);
}
