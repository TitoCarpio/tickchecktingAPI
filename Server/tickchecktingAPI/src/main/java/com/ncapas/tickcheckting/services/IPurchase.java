package com.ncapas.tickcheckting.services;

import org.springframework.data.domain.Page;

import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;

public interface IPurchase {
	void save(PurchaseDTO info, User user, TicketCategory ticketCat) throws Exception;
	Page<Purchase> findPurchaseByUser(User user, int page, int size);	
}
