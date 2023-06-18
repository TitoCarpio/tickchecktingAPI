package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.User;

public interface UserRepo
	extends ListCrudRepository<User,UUID>{
	User findByEmail(String email);
	User findByCode(UUID code);
	User findOneByUsernameOrEmail(String username, String email);
	
}
