package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.Transfer;

public interface TransferRepo extends ListCrudRepository<Transfer, UUID> {
	List<Transfer> findByTicketCode(Ticket code);
	Transfer findByCode(UUID code);

	Transfer findBySenderCodeAndTicketCode(UUID sender, UUID ticket);

}
