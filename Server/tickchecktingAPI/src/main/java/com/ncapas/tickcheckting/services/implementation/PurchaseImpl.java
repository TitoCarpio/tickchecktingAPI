package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

		// primero creo el purchase
		Purchase purchase = new Purchase(new Date(), new Date(), user);

		purchaseRepo.save(purchase);

		// obtener la ultima compra realizada

		// creo las variables de tipo int para la pagina y el tamanio
		int page = 0, size = 20;
		// mando a llamar a la primera pagina de todas las compras
		Pageable pageable = PageRequest.of(page, size);
		// hago la peticion
		Page<Purchase> purchases = purchaseRepo.findByUserCode(user.getCode(), pageable);

		// obtengo la cantidad de paginas maximas
		int pagMax = purchases.getTotalPages() - 1;
		// cambio la variable pageble
		pageable = PageRequest.of(pagMax, size);
		// hago de nuevo la peticion para obtener los ultimos elementos de la lista
		purchases = purchaseRepo.findByUserCode(user.getCode(), pageable);

		// obtengo la lista de compras que viene en esa peticion
		List<Purchase> compras = purchases.getContent();

		// creo la cantidad de tickets con el id de la ultima compra
		for (int i = 0; i < info.getCantidad(); i++) {
			Ticket ticket = new Ticket(false, new Date(), ticketCat,
			// agrego el ticket a la ultima compra del usuario
//					purchases.getContent().get(tamanio)
					compras.get(compras.size() - 1));
			ticketRepo.save(ticket);
		}

	}

	@Override
	public Page<Purchase> findPurchaseByUser(User user, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Purchase> purchase = purchaseRepo.findByUserCode(user.getCode(), pageable);
		return purchase;

	}

}
