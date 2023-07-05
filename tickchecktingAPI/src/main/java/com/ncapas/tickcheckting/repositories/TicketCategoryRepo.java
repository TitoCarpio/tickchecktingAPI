package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.TicketCategory;

public interface TicketCategoryRepo extends ListCrudRepository<TicketCategory, UUID> {
//	TicketCategory findByNameAndEvent_id(UUID code, String name);
	List<TicketCategory> findByName(String name);

	TicketCategory findByCode(UUID code);

//	List<TicketCategory> findByEventCode(UUID ticket);

}
