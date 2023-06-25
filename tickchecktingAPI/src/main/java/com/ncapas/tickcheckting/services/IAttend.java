package com.ncapas.tickcheckting.services;

import java.util.List;
import java.util.UUID;

import com.ncapas.tickcheckting.models.entities.Attend;
import com.ncapas.tickcheckting.models.entities.Ticket;

public interface IAttend {
	void save(Ticket info) throws Exception;
	void deleteByCode(String code) throws Exception;
	Attend findByCode(String code);
	List<Attend> findAll();
	
	Attend findByTicketCode(UUID code);

}
