package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.entities.Attend;

public interface AttendRepo 
	extends ListCrudRepository<Attend, UUID>{

}
