package com.ncapas.tickcheckting.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.EventXArtist;
import com.ncapas.tickcheckting.repositories.EventXArtistRepo;
import com.ncapas.tickcheckting.services.IEventXArtist;

import jakarta.transaction.Transactional;

@Service
public class EventArtistImpl implements IEventXArtist {
	
	@Autowired
	EventXArtistRepo eArtistRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(EventXArtist info) {
		eArtistRepo.save(info);
	}

	@Override
	public List<EventXArtist> findUserPermision() {
		// TODO Auto-generated method stub
		return null;
	}

}
