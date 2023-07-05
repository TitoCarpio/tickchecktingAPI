package com.ncapas.tickcheckting.services.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.RestOneEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateEventDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.models.entities.TicketCategory;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.repositories.ECategoryRepo;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.repositories.EventXArtistRepo;
import com.ncapas.tickcheckting.repositories.EventXSponsorRepo;
import com.ncapas.tickcheckting.repositories.PlaceRepo;
import com.ncapas.tickcheckting.repositories.SponsorRepo;
import com.ncapas.tickcheckting.repositories.TicketCategoryRepo;
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
	
	@Autowired
	EventXArtistRepo eXArtistRepo;
	
	@Autowired
	EventXSponsorRepo eSponsorRepo;
	
	@Autowired
	TicketCategoryRepo tCatRepo;
	
	@Autowired
	ArtistRepo artistRespo;
	
	@Autowired
	PlaceRepo placeRepo;
	
	
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
	public Page<Event> findAll(int page,int size) {
		//creo una variable de tipo Pageable
		Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
		
		Page<Event> eventos = eventRepository.findAll(pageable);
	
		return eventos;
	}



	@Override
	public Event findByName(String name) {
		Event newEvent = eventRepository.findByName(name);
		return newEvent;
	}



	@Override
	public RestOneEventDTO findOneByCode(String code) {
		//creo un objeto de tipo RestOneEventDTO
		RestOneEventDTO newEvent;
		
		// busco el evento en la tabla de eventos
		Event evento = eventRepository.findByCode(UUID.fromString(code));
		
		//busco la categoria
		EventCategory eCat = evCategoryRepo.findByCode(evento.getEventCategory().getCode());
		
		//obtengo la lista de artistas
		List<EventXArtist> artistas = eXArtistRepo.findByEventCode(evento.getCode());
		//recorro la lista de artistas para pasarla a una de tipo artist
		List<Artist> listArtist = new ArrayList<>();
		for(EventXArtist artist : artistas) {
			Artist nuevo = artistRespo.findByCode(artist.getArtist().getCode());
			listArtist.add(nuevo);
		}
		
		//obtengo la lista de los sponosr
		List<EventXSponsor> sponsors =  eSponsorRepo.findByEventCode(evento.getCode());
		//recorro la lista para pasarlaa una de tipo sponsor
		List<Sponsor> listSponsor = new ArrayList<>();
		for(EventXSponsor sponsor : sponsors) {
			Sponsor nuevo = sponsorRepo.findByCode(sponsor.getSponsor().getCode());
			listSponsor.add(nuevo);
		}
		
		//obtengo la lista de los tickets
		List<TicketCategory> ticketsCat = tCatRepo.findAll();
		List<TicketCategory> tickEvento = new ArrayList<>();
		for(TicketCategory tiket : ticketsCat) {
			if(tiket.getEvent_id().getCode().equals(evento.getCode())) {
				tickEvento.add(tiket);
			}
		}
		
		//obtengo el lugar
		Place place = placeRepo.findByCode(evento.getPlace_id().getCode());
		
		newEvent = new RestOneEventDTO(
				evento.getCode(), 
				evento.getName(), 
				eCat, 
				evento.getEvent_date(), 
				evento.getEventHour(),
				evento.getImagen(), 
				place, 
				listArtist, 
				listSponsor, 
				tickEvento);
		
		return newEvent;
	}



	@Override
	public void updateEvent(UpdateEventDTO info ,Place place, EventCategory category) {
		//busco el evento a modificar
		Event evento = eventRepository.findByCode(UUID.fromString(info.getCode()));
		
		//actualizo los campos
		evento.setName(info.getName());
		evento.setEvent_date(info.getEventDate());
		evento.setEventHour(info.getEventHour());
		evento.setImagen(info.getImage());
		//busco la categoria
		
		evento.setEventCategory(category);
		evento.setPlace_id(place);
		
		//guardo la actualizacion del evento
		eventRepository.save(evento);
		
	}
	
	
	
	
	
	

}
