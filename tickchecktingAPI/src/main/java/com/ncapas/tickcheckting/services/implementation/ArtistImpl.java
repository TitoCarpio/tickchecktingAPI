package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.NewArtistDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateArtistDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.repositories.EventRepo;
import com.ncapas.tickcheckting.repositories.EventXArtistRepo;
import com.ncapas.tickcheckting.services.IArtist;

import jakarta.transaction.Transactional;

@Service
public class ArtistImpl implements IArtist{
	
	@Autowired
	ArtistRepo artistRepo;
	
	@Autowired
	EventRepo eventRepo;
	
	@Autowired
	EventXArtistRepo eArtistRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(String code, String eventCode) throws Exception {
		EventXArtist busqueda = eArtistRepo.findByEventCodeAndArtistCode(UUID.fromString(eventCode),UUID.fromString(code));
		//borro ese elemento de la lista
		eArtistRepo.delete(busqueda);
	
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(UpdateArtistDTO info) {
		//busco el artista 
		Artist artistaB = artistRepo.findByCode(UUID.fromString(info.getCodeArtist()));
		//actualizo el nombre del artista
		artistaB.setName(info.getName());
		//guardo esos cambios en la tabla
		artistRepo.save(artistaB);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(NewArtistDTO info) throws Exception {
		//creo un nuevo objeto de tipo artista
		Artist nuevo = new Artist(
				info.getNameArtist(),
				new Date()
				);
		
		//busco el evento con el codigo de info
		Event evento = eventRepo.findByCode(UUID.fromString(info.getCodeEvent()));
		
		//creo un nuevo objeto de tipo EventXArtis
		EventXArtist eArtist = new EventXArtist(
				new Date(),
				nuevo,
				evento
				);
		
		// bueco el artista para ver si existe
		Artist artistaB = artistRepo.findByName(info.getNameArtist());
		
		if (artistaB == null) {
			//ahora creo el artista en la tabla de artista
			artistRepo.save(nuevo);
			
			//agrego el artista a la tabla de EventXArtist
			eArtistRepo.save(eArtist);
		}
		
	}

	@Override
	public void delete(String code) throws Exception {
		List<EventXArtist> busqueda = eArtistRepo.findByArtistCode(UUID.fromString(code));
		//borro ese elemento de la lista
		eArtistRepo.deleteAll(busqueda);
		//busco el artista y lo borro
		Artist artista =  artistRepo.findByCode(UUID.fromString(code));
		artistRepo.delete(artista);
		
	}
	
	

	
	
	
	
	
	
}
