package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.Attend;
import com.ncapas.tickcheckting.models.entities.Ticket;
import com.ncapas.tickcheckting.repositories.AttendRepo;
import com.ncapas.tickcheckting.services.IAttend;

import jakarta.transaction.Transactional;

@Service
public class AttendImpl implements IAttend{
	
	@Autowired
	AttendRepo attendRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(Ticket info) throws Exception {
		Attend attend = new Attend(
				new Date(),
				new Date(),
				info.getPurchase().getUser(),
				info,
				info.getCategory_id().getEvent_id()
				);
		
		attendRepo.save(attend);
	}

	@Override
	public void deleteByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Attend findByCode(String code) {
		
		return null;
	}

	@Override
	public List<Attend> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attend findByTicketCode(UUID code) {
		Attend attend = attendRepo.findByTicketCode(code);
		return attend;
	}
	
	
	
}
