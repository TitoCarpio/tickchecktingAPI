package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Ticket_Category;

public interface ICategory {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Ticket_Category findByCode(String code);
	List<Ticket_Category> findAll();
}
