package com.ncapas.tickcheckting.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.repositories.EventXSponsorRepo;
import com.ncapas.tickcheckting.services.IEventSponsor;

import jakarta.transaction.Transactional;

@Service
public class EventSponsorImpl implements IEventSponsor{
	@Autowired
	EventXSponsorRepo eSponsor;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(EventXSponsor info) {
		eSponsor.save(info);
	}
	
}
