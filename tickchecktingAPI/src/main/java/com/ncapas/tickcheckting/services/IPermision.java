package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Permision;

public interface IPermision {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Permision findByCode(String code);
	List<Permision> findAll();
}
