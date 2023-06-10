package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.Attend;

public interface IAttend {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Attend findByCode(String code);
	List<Attend> findAll();

}
