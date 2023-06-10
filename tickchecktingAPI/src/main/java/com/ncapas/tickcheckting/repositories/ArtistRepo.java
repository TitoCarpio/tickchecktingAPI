package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.entities.Artist;

public interface ArtistRepo 
	extends ListCrudRepository<Artist, UUID>{

}
