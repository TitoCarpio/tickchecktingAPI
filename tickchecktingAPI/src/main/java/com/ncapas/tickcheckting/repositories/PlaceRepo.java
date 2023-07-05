package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Place;

public interface PlaceRepo
extends ListCrudRepository<Place,UUID>{
	Place findByName(String name);
	Place findByCode(UUID code);

}
