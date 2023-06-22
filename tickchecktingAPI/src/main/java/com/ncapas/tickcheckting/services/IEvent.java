package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.dtos.RestEventDTO;
import com.ncapas.tickcheckting.models.dtos.RestOneEventDTO;
import com.ncapas.tickcheckting.models.dtos.SaveEventDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateEventDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.models.entities.Place;

public interface IEvent {
	void save(SaveEventDTO info, Place place) throws Exception;
	void deleteByCode(String code) throws Exception;
	Event findByCode(String code);
	Event findByName(String name);
	RestOneEventDTO findOneByCode(String code);
	void updateEvent(UpdateEventDTO info, Place place, EventCategory category);
	List<RestEventDTO> findAll();
}
