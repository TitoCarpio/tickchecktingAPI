package com.ncapas.tickcheckting.services.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.SaveEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveSponsorDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.repositories.ECategoryRepo;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.repositories.SponsorRepo;
import com.ncapas.tickcheckting.services.IEvent;

import jakarta.transaction.Transactional;

@Service
public class EventImpl implements IEvent{
	@Autowired
	EventRepo eventRepository;
	
	@Autowired
	ECategoryRepo evCategoryRepo;
	
	@Autowired 
	SponsorRepo sponsorRepo;
	
	//funcion que obtiene los sponsors para vincularlos con el evento
	public List<Sponsor> recorrerLista(List<SaveSponsorDTO> sponsors){
		List<Sponsor> newSponsors = new ArrayList<>();
		
		for (SaveSponsorDTO sponsor : sponsors) {
			newSponsors.add(sponsorRepo.findByName(sponsor.getName()));
		}	
		return newSponsors;
	}
	
	
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveEventDTO info, Place place) throws Exception {
	EventCategory eCat =  evCategoryRepo.findByCode(UUID.fromString(info.getCategory()));
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(info.getEventDate());
		calendar.add(Calendar.HOUR, 6);
		Date dateEvent = calendar.getTime();
		
	
		
		Event newEvent = new Event(
				info.getName(),
				dateEvent,
				info.getEventHour(),
				info.getImage(),
				new Date(),
				new Date(),
				eCat,
				place
				);
		eventRepository.save(newEvent);	
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Event findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findAll() {
		List<Event> eventos = eventRepository.findAll();
		return eventos;
	}



	@Override
	public Event findByName(String name) {
		Event newEvent = eventRepository.findByName(name);
		return newEvent;
	}
	
	

}
