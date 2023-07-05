package com.ncapas.tickcheckting.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.dtos.SavePermisionDTO;
import com.ncapas.tickcheckting.models.entities.Permision;
import com.ncapas.tickcheckting.repositories.PermisionRepo;
import com.ncapas.tickcheckting.services.IPermision;

import jakarta.transaction.Transactional;

@Service
public class PermisionImpl implements IPermision {
	
	@Autowired PermisionRepo permisionRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SavePermisionDTO info) throws Exception {
		Permision permision = new Permision(
				info.getName(),
				new Date()	
				);
		
		permisionRepo.save(permision);
	}

	@Override
	public void deleteByCode(UUID code) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Permision findByCode(UUID code) {
		Permision permision = permisionRepo.findByCode(code);
		return permision;
	}

	@Override
	public List<Permision> findAll() {
		List<Permision> permisions = permisionRepo.findAll();
		return permisions;
	}

	@Override
	public Permision findByName(String name) {
		Permision permision = permisionRepo.findByName(name);
		return permision;
	}
	
	

}
