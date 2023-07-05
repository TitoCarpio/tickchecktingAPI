package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Sponsor;

public interface SponsorRepo
extends ListCrudRepository<Sponsor,UUID>{
	Sponsor findByCode(UUID code);
	Sponsor findByName(String name);
}
