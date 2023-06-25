package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.Purchase;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.models.entities.Transfer;
import com.ncapas.tickcheckting.models.entities.User;
import com.ncapas.tickcheckting.repositories.PurchaseRepo;
import com.ncapas.tickcheckting.repositories.TicketRepo;
import com.ncapas.tickcheckting.repositories.TransferRepo;
import com.ncapas.tickcheckting.services.ITransfer;

import jakarta.transaction.Transactional;

@Service
public class TransferImpl implements ITransfer {

	@Autowired
	TransferRepo transferRepo;
	
	@Autowired
	PurchaseRepo purchaseRepo;
	
	@Autowired
	TicketRepo ticketRepo;
	
	// funcion que me genera el hashmasp
		private static String generateVerificationCode() {
			int length = 6;
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			Random random = new Random();
			StringBuilder code = new StringBuilder();

			for (int i = 0; i < length; i++) {
				int randomIndex = random.nextInt(characters.length());
				char randomChar = characters.charAt(randomIndex);
				code.append(randomChar);
			}

			return code.toString();
		}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(Ticket ticket, User sender, User reciver) throws Exception {

		Transfer transfer = new Transfer(new Date(), generateVerificationCode(), new Date(), false, ticket, sender,
				reciver);

		transferRepo.save(transfer);
	}

	@Override
	public void deleteByCode(String code) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Transfer findByCode(String code) {
		Transfer transfer = transferRepo.findByCode(UUID.fromString(code));
		return transfer;
	}

	@Override
	public List<Transfer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> findByTicket(Ticket ticket) {
		List<Transfer> transfer = transferRepo.findByTicketCode(ticket);
		return transfer;
	}

	@Override
	public void confirmTransfer(Transfer transfer) {
		transfer.setActdat(new Date());
		transfer.setStatus(true);
		
		transferRepo.save(transfer);
		
		//realizo los cambios de la compra
		Purchase purchase = new Purchase(
				new Date(),
				new Date(),
				transfer.getReciver()
				);
		//guardo estos cambios
		purchaseRepo.save(purchase);
		
		List<Purchase> purchaseB = purchaseRepo.findByUserCode(transfer.getReciver().getCode());
		
		purchase = purchaseB.get(purchaseB.size()-1);
		
		Ticket ticket = transfer.getTicket();
		ticket.setPurchase(purchase);
		
		ticketRepo.save(ticket);
		//busco esa compra
		
	}
	
	
	

	

}
