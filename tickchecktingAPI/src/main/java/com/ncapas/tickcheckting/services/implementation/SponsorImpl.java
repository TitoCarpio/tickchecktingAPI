package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.repositories.SponsorRepo;
import com.ncapas.tickcheckting.services.ISponsor;

import jakarta.transaction.Transactional;
@Service
public class SponsorImpl implements ISponsor {
	@Autowired
	SponsorRepo sponsorRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(String name) throws Exception {
		Sponsor newSponsor = new Sponsor(name, new Date());
		sponsorRepo.save(newSponsor);
		
		
	}
	

}
