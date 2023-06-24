package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.PurchaseDTO;
import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.PurchaseRepo;
import com.ncapas.tickcheckting.repositories.TicketRepo;
import com.ncapas.tickcheckting.services.IPurchase;

import jakarta.transaction.Transactional;

@Service
public class PurchaseImpl implements IPurchase {
	
	@Autowired
	PurchaseRepo purchaseRepo;
	
	@Autowired
	TicketRepo ticketRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(PurchaseDTO info, User user, TicketCategory ticketCat) throws Exception {
		
		//primero creo el purchase
		Purchase purchase = new Purchase(
				new Date(),
				new Date(),
				user
				);
		
		purchaseRepo.save(purchase);
		
		//busco la compra mediante el codigo del usuario
		List<Purchase> purchases =  purchaseRepo.findByUserCode(user.getCode());
		
		System.out.println(purchases);
		
		//creo la cnatidad de tickets con el id de la compra
		for(int i = 0; i < info.getCantidad(); i++) {
			Ticket ticket = new Ticket(
					false,
					new Date(),
					ticketCat,
					purchases.get(purchases.size()-1)
					);
			ticketRepo.save(ticket);
		}		
	}

	@Override
	public List<Purchase> findPurchaseByUser(User user) {
		List<Purchase> purchase = purchaseRepo.findByUserCode(user.getCode());
		return purchase;
	}

	
	
	
	

}
