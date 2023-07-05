package com.ncapas.tickcheckting.services;

import java.util.List;
import java.util.UUID;

import com.ncapas.tickcheckting.models.dtos.SavePermisionDTO;
import com.ncapas.tickcheckting.models.entities.Permision;

public interface IPermision {
	void save(SavePermisionDTO info) throws Exception;
	void deleteByCode(UUID code) throws Exception;
	Permision findByCode(UUID code);
	Permision findByName(String name);
	List<Permision> findAll();
}
