package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.DeletePlaceDTO;
import com.ncapas.tickcheckting.models.dtos.SavePlaceDTO;
import com.ncapas.tickcheckting.models.entities.Place;
import com.ncapas.tickcheckting.repositories.PlaceRepo;
import com.ncapas.tickcheckting.services.IPlace;

import jakarta.transaction.Transactional;

@Service
public class PlaceImpl implements IPlace {

	@Autowired
	PlaceRepo placeRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SavePlaceDTO info) throws Exception {
		Place place = new Place(info.getName(), new Date(), new Date());
		placeRepository.save(place);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(DeletePlaceDTO info) throws Exception {
		placeRepository.deleteById(UUID.fromString(info.getPlaceCode()));

	}

	@Override
	public List<Place> findAll() {
		List<Place> places = placeRepository.findAll();
		return places;
	}
	
	

}
