package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Ticket;

public interface ITicket {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Ticket findByCode(String code);
	List<Ticket> findAll();
}
