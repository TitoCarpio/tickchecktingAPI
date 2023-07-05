package com.ncapas.tickcheckting.services.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.tickcheckting.models.entities.UserXPermision;
import com.ncapas.tickcheckting.repositories.UserXPermisionRepo;
import com.ncapas.tickcheckting.services.IUserXPermision;

import jakarta.transaction.Transactional;

@Service
public class UserPermisionImpl implements IUserXPermision{
	@Autowired UserXPermisionRepo uPermisionRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(UserXPermision info) throws Exception {
		uPermisionRepo.save(info);
	}

	@Override
	public List<UserXPermision> findUser(UUID user) {
		List<UserXPermision> userPermision = uPermisionRepo.findByUserCode(user);
		return userPermision;
	}
	
}
