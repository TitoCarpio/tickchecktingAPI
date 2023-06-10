package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.entities.Transfer;

public interface TransferRepo
	extends ListCrudRepository<Transfer, UUID>{

}
