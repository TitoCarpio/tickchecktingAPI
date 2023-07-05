package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.dtos.DeletePlaceDTO;
import com.ncapas.tickcheckting.models.dtos.SavePlaceDTO;
import com.ncapas.tickcheckting.models.entities.Place;

public interface IPlace {
	void save (SavePlaceDTO info) throws Exception;
	void delete(DeletePlaceDTO info) throws Exception;
	List<Place> findAll();

}
