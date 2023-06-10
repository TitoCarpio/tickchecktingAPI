package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.EventXArtist;

public interface IEventXArtist {
	void save() throws Exception;
	List<EventXArtist> findUserPermision();
}
