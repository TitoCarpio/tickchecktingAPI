package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.dtos.SaveECategoryDTO;
import com.ncapas.tickcheckting.models.entities.EventCategory;

public interface IEventCategory {
	void save(SaveECategoryDTO info) throws Exception;
	void deleteByCode(String code) throws Exception;
	EventCategory findByName(String name);
	List<EventCategory> findAll();
}
