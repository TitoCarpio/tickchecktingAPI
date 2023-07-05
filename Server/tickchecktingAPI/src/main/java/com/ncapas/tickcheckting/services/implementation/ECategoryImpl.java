package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.SaveECategoryDTO;
import com.ncapas.tickcheckting.models.entities.EventCategory;
import com.ncapas.tickcheckting.repositories.ECategoryRepo;
import com.ncapas.tickcheckting.services.IEventCategory;

@Service
public class ECategoryImpl implements IEventCategory {
	
	@Autowired
	ECategoryRepo eCategoryRepo;
	@Override
	public void save(SaveECategoryDTO info) throws Exception {
		EventCategory nuevo = new EventCategory(
				info.getName(),
				new Date(),
				new Date()
				);
		eCategoryRepo.save(nuevo);
	}

	@Override
	public void deleteByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventCategory findByName(String name) {
		EventCategory eCategory = eCategoryRepo.findByName(name);
		return eCategory;
	}

	@Override
	public List<EventCategory> findAll() {
		List<EventCategory> eCategory = eCategoryRepo.findAll();
		return eCategory;
	}
	

}
