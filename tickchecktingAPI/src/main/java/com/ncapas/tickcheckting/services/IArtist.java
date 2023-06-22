package com.ncapas.tickcheckting.services;

import com.ncapas.tickcheckting.models.dtos.UpdateArtistDTO;

public interface IArtist {
//	void save() throws Exception;
	void deleteByCode(String code, String eventCode) throws Exception;

	void update(UpdateArtistDTO info) throws Exception;
//	Artist findByCode(String code);
//	List<Artist> findAll();
}
