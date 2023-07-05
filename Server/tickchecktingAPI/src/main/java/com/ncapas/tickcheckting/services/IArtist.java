package com.ncapas.tickcheckting.services;

import com.ncapas.tickcheckting.models.dtos.NewArtistDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateArtistDTO;

public interface IArtist {
	void save(NewArtistDTO info) throws Exception;
	void deleteByCode(String code, String eventCode) throws Exception;
	void delete(String code) throws Exception;
	void update(UpdateArtistDTO info) throws Exception;
//	Artist findByCode(String code);
//	List<Artist> findAll();
}
