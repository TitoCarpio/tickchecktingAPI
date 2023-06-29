package com.ncapas.tickcheckting.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncapas.tickcheckting.models.dtos.MessageDTO;
import com.ncapas.tickcheckting.models.dtos.PageDTO;
import com.ncapas.tickcheckting.models.dtos.RestEventDTO;
import com.ncapas.tickcheckting.models.dtos.RestOneEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveArtistDTO;
import com.ncapas.tickcheckting.models.dtos.SaveEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.SaveTicketCatDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateEventDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.repositories.ECategoryRepo;
import com.ncapas.tickcheckting.repositories.EventXArtistRepo;
import com.ncapas.tickcheckting.repositories.EventXSponsorRepo;
import com.ncapas.tickcheckting.repositories.PlaceRepo;
import com.ncapas.tickcheckting.repositories.SponsorRepo;
import com.ncapas.tickcheckting.services.IEvent;
import com.ncapas.tickcheckting.services.IEventSponsor;
import com.ncapas.tickcheckting.services.IEventXArtist;
import com.ncapas.tickcheckting.services.ITicketCat;
import com.ncapas.tickcheckting.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/v1/tickcheck/")
public class EventController {
	@Autowired
	IEvent eventServices;

	@Autowired
	PlaceRepo placeRepository;

	@Autowired
	SponsorRepo sponsorRepo;

	@Autowired
	IEventSponsor eSponsorServices;

	@Autowired
	EventXSponsorRepo eSponsorRepo;

	@Autowired
	ArtistRepo artistRepo;

	@Autowired
	EventXArtistRepo eArtistRepo;

	@Autowired
	IEventXArtist eArtistServices;
	
	@Autowired
	ITicketCat ticketCatServices;
	
	@Autowired
	ECategoryRepo eCatRepo;

	@Autowired
	private RequestErrorHandler errorHandler;

	// funcion que recorre el arreglo de sponsor y verifica su existencia
	public void recorrerLista(List<SaveSponsorDTO> sponsors) {
		// verifico la existencia de los sponsor
		for (SaveSponsorDTO sponsor : sponsors) {
			Sponsor newSponsor = sponsorRepo.findByName(sponsor.getName());
			// si el sponsor no existe mando a crearlo
			if (newSponsor == null) {
				newSponsor = new Sponsor(sponsor.getName(), new Date());
				sponsorRepo.save(newSponsor);
			}

		}

	}

	// funcion que recorre el arreglo de artistas y verifica su existencia, si no
	// existe lo crea
	public void recorrerListaArtista(List<SaveArtistDTO> artists) {
		// verifica la existencia de los artistas
		for (SaveArtistDTO artist : artists) {
			Artist artista = artistRepo.findByName(artist.getName());
			if (artista == null) {
				artista = new Artist(artist.getName(), new Date());
				// creo el artista
				artistRepo.save(artista);
			}
		}

	}

	// Funcion que recorre la lista de esponsor y llena una lista de tipo sponsor
	public List<Sponsor> listaSponsor(List<SaveSponsorDTO> sponsors) {
		List<Sponsor> newSponsor = new ArrayList<>();
		for (SaveSponsorDTO sponsor : sponsors) {
			Sponsor sponsorB = sponsorRepo.findByName(sponsor.getName());
			newSponsor.add(sponsorB);

		}
		return newSponsor;
	}

	// Funcion que recorre la lista de artistas y llena una lista de los artistas
	// traidos de la base
	public List<Artist> listaArtista(List<SaveArtistDTO> artists) {
		List<Artist> newArtist = new ArrayList<>();
		for (SaveArtistDTO artist : artists) {
			Artist artistaB = artistRepo.findByName(artist.getName());
			newArtist.add(artistaB);
		}
		return newArtist;
	}

	// funcion que llena la tabla EventXSponsor y verifica que el dato no exista ya
	public void llenarTablaESponsor(List<Sponsor> sponsors, Event evento) {
		for (Sponsor sponsor : sponsors) {
			EventXSponsor eSponsor = eSponsorRepo.findByEventCodeAndSponsorCode(evento.getCode(), sponsor.getCode());
			if (eSponsor == null) {
				EventXSponsor nuevo = new EventXSponsor(new Date(), evento, sponsor);
				eSponsorServices.save(nuevo);
			}
		}

	}

	// funcion que llena la tabla de EventXArtist y verifica que el dato no exista
	// ya
	public void llenarTablaEArtist(List<Artist> artistas, Event evento) {
		for (Artist artist : artistas) {
			EventXArtist eArtist = eArtistRepo.findByEventCodeAndArtistCode(evento.getCode(), artist.getCode());
			if (eArtist == null) {
				EventXArtist nuevo = new EventXArtist(new Date(), artist, evento);
				eArtistServices.save(nuevo);

			}
		}

	}

	@PostMapping("saveEvent")
	public ResponseEntity<?> save(@RequestBody @Valid SaveEventDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}

		try {
			// verifico si existe un evento con el mismo nombre
			Event eventExiste = eventServices.findByName(info.getName());
			if (eventExiste != null) {
				return new ResponseEntity<>("And there is an event with that name", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Place place = placeRepository.findByCode(UUID.fromString(info.getPlaceCode()));
			if (place != null) {
				// mando a llamar a la funcion que recorre la lista de sponsors
				recorrerLista(info.getSponsor());
				List<Sponsor> sponsors = listaSponsor(info.getSponsor());
				eventServices.save(info, place);
				// busco el evento recien creado
				Event evento = eventServices.findByName(info.getName());
				// llenando la tabla de EventXSponsr
				llenarTablaESponsor(sponsors, evento);
				// llenando la tabla de artista
				recorrerListaArtista(info.getArtists());
				List<Artist> artistas = listaArtista(info.getArtists());
				llenarTablaEArtist(artistas, evento);
				
				//recorriendo el arreglo de laas cat de tickets y crearlos
				for(SaveTicketCatDTO ticket : info.getTicket()) {
					boolean verdad =ticketCatServices.find(ticket.getName(), evento.getCode());
					if ( verdad) {
						ticketCatServices.save(ticket, evento);
					}
				}

				return new ResponseEntity<>(new MessageDTO("Event created"), HttpStatus.CREATED);

			}
			return new ResponseEntity<>("Creation error, place not found", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//funcion que recorre los elementos que trae la paginacion y obtiene los datos a mostrar
	public List<RestEventDTO> events(List<Event> events){
		List<RestEventDTO> transformation =  new ArrayList<>();
		
		for(Event event : events) {
			RestEventDTO evento = new RestEventDTO(
					event.getCode(), 
					event.getName(), 
					event.getEvent_date(), 
					event.getEventHour(), 
					event.getImagen(), 
					event.getPlace_id().getName());
			transformation.add(evento);	
		}
		
		return transformation;
	}
	
	
	@GetMapping("allEvents")
	public ResponseEntity<?> allEvents(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "16") int size) {
		Page<Event> eventos = eventServices.findAll(page, size);
		//transformo los datos
		List<RestEventDTO> transformation = events(eventos.getContent());
		
		//creo el DTO de respuesta
		PageDTO<RestEventDTO> response = new PageDTO<>(
				transformation,
				eventos.getNumber(),
				eventos.getSize(),
				eventos.getTotalElements(),
				eventos.getTotalPages()
				);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("oneEvent/{code}")
	public ResponseEntity<?> findOne(@PathVariable String code){
		if (!code.equals(null)) {
			RestOneEventDTO evento = eventServices.findOneByCode(code);
			return new ResponseEntity<>(evento, HttpStatus.OK);
		}else
			return new ResponseEntity<>("No se encontraron datos",HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	//actualizando un evento
	@PutMapping("updateEvent")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateEventDTO info, BindingResult validations ){
		if (validations.hasErrors()) {
			return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		//buscando el lugar y la categoria
		Place lugar = placeRepository.findByCode(UUID.fromString(info.getPlaceCode()));
		EventCategory categoria = eCatRepo.findByCode(UUID.fromString(info.getCategory()));
		
		if (lugar == null || categoria == null) {
			return new ResponseEntity<>("Place or category not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			eventServices.updateEvent(info, lugar, categoria);
			return new ResponseEntity<>(new MessageDTO("Event updated"), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
