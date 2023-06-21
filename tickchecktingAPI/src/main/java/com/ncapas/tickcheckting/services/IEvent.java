package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.dtos.SaveEventDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.Place;

public interface IEvent {
	void save(SaveEventDTO info, Place place) throws Exception;
	void deleteByCode(String code) throws Exception;
	Event findByCode(String code);
	Event findByName(String name);
	List<Event> findAll();
}
