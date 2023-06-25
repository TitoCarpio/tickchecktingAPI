package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.Transfer;
import com.ncapas.tickcheckting.models.entities.User;

public interface ITransfer {
	void save(Ticket ticket, User sender, User reciver) throws Exception;
	void deleteByCode(String code) throws Exception;
	Transfer findByCode(String code);
	List<Transfer> findByTicket(Ticket ticket);
	List<Transfer> findAll();
	
	void confirmTransfer(Transfer transfer);
}
