package com.ncapas.tickcheckting.services.implementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.UpdateArtistDTO;
import com.ncapas.tickcheckting.models.entities.Artist;
import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.repositories.ArtistRepo;
import com.ncapas.tickcheckting.repositories.EventXArtistRepo;
import com.ncapas.tickcheckting.services.IArtist;

import jakarta.transaction.Transactional;

@Service
public class ArtistImpl implements IArtist{
	
	@Autowired
	ArtistRepo artistRepo;
	
	@Autowired
	EventXArtistRepo eArtistRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(String code, String eventCode) throws Exception {
		EventXArtist busqueda = eArtistRepo.findByEventCodeAndArtistCode(UUID.fromString(eventCode),UUID.fromString(code));
		//borro ese elemento de la lista
		eArtistRepo.delete(busqueda);
		
		//busco el artista y lo borro
		Artist artista =  artistRepo.findByCode(UUID.fromString(code));
		artistRepo.delete(artista);
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

//	@Override
//	public Artist findByCode(String code) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Artist> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
	
	
}
