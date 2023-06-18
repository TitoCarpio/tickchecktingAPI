package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Permision;

public interface PermisionRepo 
	extends ListCrudRepository<Permision, UUID>{
	Permision findByCode(UUID code);
	Permision findByName(String name);
	

}
