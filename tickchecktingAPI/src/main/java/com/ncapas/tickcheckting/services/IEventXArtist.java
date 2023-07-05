package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.EventXArtist;

public interface IEventXArtist {
	void save(EventXArtist info) ;
	List<EventXArtist> findUserPermision();
}
