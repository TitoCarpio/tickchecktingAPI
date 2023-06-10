package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.Artist;

public interface IArtist {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Artist findByCode(String code);
	List<Artist> findAll();
}
