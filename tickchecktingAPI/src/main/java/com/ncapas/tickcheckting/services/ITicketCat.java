package com.ncapas.tickcheckting.services;

import java.util.UUID;

import com.ncapas.tickcheckting.models.dtos.SaveTicketCatDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.TicketCategory;

public interface ITicketCat {
	void save(SaveTicketCatDTO info, Event event) throws Exception;
	boolean find(String name, UUID event);
	void delete(UUID code)throws Exception;
	TicketCategory findByCode(UUID code);

}
