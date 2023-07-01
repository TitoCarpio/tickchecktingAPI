package com.ncapas.tickcheckting.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.SaveTicketCatDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateTicketCatDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.repositories.TicketCategoryRepo;
import com.ncapas.tickcheckting.services.ITicketCat;

import jakarta.transaction.Transactional;

@Service
public class TicketCatImpl implements ITicketCat {
	
	@Autowired
	TicketCategoryRepo tCatRepo;
	
	@Autowired
	EventRepo eventRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveTicketCatDTO info, Event event) throws Exception {
		TicketCategory nuevo = new TicketCategory(
				info.getName(),
				info.getPrice(),
				info.getQty(),
				new Date(),
				new Date(),
				event
				);
		tCatRepo.save(nuevo);	
	}

	@Override
	public boolean find(String name, UUID event) {
		List<TicketCategory> nuevo = tCatRepo.findByName(name);
		TicketCategory tickCat = null;
//		TicketCategory nuevo = tCatRepo.findByNameAndEventCode(name);
		for(TicketCategory tiket : nuevo) {
			if (tiket.getEvent_id().getCode().equals(event)) {
				tickCat = tiket;
			}
		}
		
		if (tickCat == null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void delete(UUID code) throws Exception {
		tCatRepo.deleteById(code);
		
	}

	@Override
	public TicketCategory findByCode(UUID code) {
		TicketCategory buscado = tCatRepo.findByCode(code);
		return buscado;
	}

	@Override
	public void update(UpdateTicketCatDTO elemento) {
		TicketCategory buscado = tCatRepo.findByCode(elemento.getCode());
		
		buscado.setName(elemento.getName());
		buscado.setPrice(elemento.getPrice());
		buscado.setQty(elemento.getQty());
		
		//despues de haber actualizado los campos lo guardo en la base 
		tCatRepo.save(buscado);
		
		
	}

	@Override
	public List<TicketCategory> findByEventCode(UUID event) {
		//busco el evento
		Event evento = eventRepo.findByCode(event);
		List<TicketCategory> ticketsCat = tCatRepo.findAll();
		List<TicketCategory> tickEvento = new ArrayList<>();
		for(TicketCategory tiket : ticketsCat) {
			if(tiket.getEvent_id().getCode().equals(evento.getCode())) {
				tickEvento.add(tiket);
			}
		}
		
		return tickEvento;
	}
	
	
	
	
	
	
	
	
	
	

}
