package com.ncapas.tickcheckting.services;

import java.util.UUID;

import com.ncapas.tickcheckting.models.dtos.NewSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateSponsorDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.models.entities.Sponsor;

public interface ISponsor {
	void save(String name) throws Exception;
	void update(UpdateSponsorDTO info, Sponsor sponsor);
	Sponsor findByCode(UUID code);
	void newSponsor(NewSponsorDTO info, Event event) throws Exception;
	void delete(EventXSponsor element) throws Exception;

}
