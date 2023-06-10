package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Purchase;

public interface IPurchase {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Purchase findByCode(String code);
	List<Purchase> findAll();

}
