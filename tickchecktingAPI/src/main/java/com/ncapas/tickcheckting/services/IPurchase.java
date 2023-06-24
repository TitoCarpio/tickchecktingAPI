package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;

public interface IPurchase {
	void save(PurchaseDTO info, User user, TicketCategory ticketCat) throws Exception;
	List<Purchase> findPurchaseByUser(User user);
	
}
