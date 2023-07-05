package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.NewSponsorDTO;
import com.ncapas.tickcheckting.models.dtos.UpdateSponsorDTO;
import com.ncapas.tickcheckting.models.entities.Event;
import com.ncapas.tickcheckting.models.entities.EventXSponsor;
import com.ncapas.tickcheckting.models.entities.Sponsor;
import com.ncapas.tickcheckting.repositories.EventXSponsorRepo;
import com.ncapas.tickcheckting.repositories.SponsorRepo;
import com.ncapas.tickcheckting.services.ISponsor;

import jakarta.transaction.Transactional;
@Service
public class SponsorImpl implements ISponsor {
	@Autowired
	SponsorRepo sponsorRepo;
	
	@Autowired
	EventXSponsorRepo eSponsorRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(String name) throws Exception {
		Sponsor newSponsor = new Sponsor(name, new Date());
		sponsorRepo.save(newSponsor);	
	}

	@Override
	public void update(UpdateSponsorDTO info, Sponsor sponsor) {
		//actualizo los campos
		sponsor.setName(info.getName());
		
		//guardo el cambio en la base
		sponsorRepo.save(sponsor);
	}

	@Override
	public Sponsor findByCode(UUID code) {
		Sponsor sponsor = sponsorRepo.findByCode(code);
		return sponsor;
	}

	@Override
	public void newSponsor(NewSponsorDTO info, Event event) throws Exception {
		//primero verifico que no exista un sponsor con ese nombre
		Sponsor sponsor = sponsorRepo.findByName(info.getName());
		
		if (sponsor == null) {
			sponsor = new Sponsor(
					info.getName(),
					new Date()
					);
			//creo el sponsor
			sponsorRepo.save(sponsor);
			//lo mando a buscar para obtener su code y agregarlo a la tablas cruz
			sponsor = sponsorRepo.findByName(info.getName());
			//creo un objeto de tipo EventxSponsor
			EventXSponsor eSponsor =  new EventXSponsor(
					new Date(),
					event,
					sponsor
					);
			//lo agrego a la tabla cruz
			eSponsorRepo.save(eSponsor);
			
		}else {
			//verifico que no exista para ese usuario
			EventXSponsor eSponsorB = eSponsorRepo.findByEventCodeAndSponsorCode(event.getCode(), sponsor.getCode());
			
			if (eSponsorB == null) {
				//creo un objeto de tipo EventxSponsor
				EventXSponsor eSponsor =  new EventXSponsor(
						new Date(),
						event,
						sponsor
						);
				//lo agrego a la tabla cruz
				eSponsorRepo.save(eSponsor);
				
			}
			
		}
		
	}

	@Override
	public void delete(EventXSponsor element) throws Exception {
		eSponsorRepo.delete(element);
	}
	

}
