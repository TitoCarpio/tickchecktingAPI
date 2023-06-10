package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.Transfer;

public interface ITransfer {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Transfer findByCode(String code);
	List<Transfer> findAll();
	

}
