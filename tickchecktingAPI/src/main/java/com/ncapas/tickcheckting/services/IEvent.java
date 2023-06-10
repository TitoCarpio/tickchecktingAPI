package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.Event;

public interface IEvent {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Event findByCode(String code);
	List<Event> findAll();
}
