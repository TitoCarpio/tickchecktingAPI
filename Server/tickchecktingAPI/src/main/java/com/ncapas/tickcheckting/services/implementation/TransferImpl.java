package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

		// realizo los cambios de la compra
		Purchase purchase = new Purchase(new Date(), new Date(), transfer.getReciver());
		// guardo estos cambios
		purchaseRepo.save(purchase);

		// obtener la ultima compra realizada

		// creo las variables de tipo int para la pagina y el tamanio
		int page = 0, size = 20;
		// mando a llamar a la primera pagina de todas las compras
		Pageable pageable = PageRequest.of(page, size);
		// hago la peticion
		Page<Purchase> purchases = purchaseRepo.findByUserCode(transfer.getReciver().getCode(), pageable);

		// obtengo la cantidad de paginas maximas
		int pagMax = purchases.getTotalPages() - 1;
		// cambio la variable pageble
		pageable = PageRequest.of(pagMax, size);
		// hago de nuevo la peticion para obtener los ultimos elementos de la lista
		purchases = purchaseRepo.findByUserCode(transfer.getReciver().getCode(), pageable);

		// obtengo la lista de compras que viene en esa peticion
		List<Purchase> compras = purchases.getContent();

//		//creo las variables de tipo int para la pagina y el tamanio
//				int page =0, size = 5;
//				//creo la variable para la paginacion
//				//creo una variable de tipo Pageable
//				Pageable pageable = PageRequest.of(page, size); 
//				//busco la compra mediante el codigo del usuario
//				Page<Purchase> purchases =  purchaseRepo.findByUserCode(transfer.getReciver().getCode(), pageable);
//		Page<Purchase> purchaseB = purchaseRepo.findByUserCode(transfer.getReciver().getCode(),pageable);
//		
//		//obtengo la cantidad maximas que tiene
//				int pageMax =  purchases.getTotalPages();
//				//obtengo esa pagina
//				pageable = PageRequest.of(pageMax, size);
//				purchases =  purchaseRepo.findByUserCode(transfer.getReciver().getCode(), pageable);
//				
////				System.out.println(purchases);
////				obtengo el tamanio de la lista  de compras
//				int tamanio = purchases.getContent().size();

//		List<Purchase> purchaseB = purchaseRepo.findByUserCode(transfer.getReciver().getCode());

		purchase = compras.get(compras.size() -1); 

		Ticket ticket = transfer.getTicket();
		ticket.setPurchase(purchase);

		ticketRepo.save(ticket);
		// busco esa compra

	}

}
