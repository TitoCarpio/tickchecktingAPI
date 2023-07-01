package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncapas.tickcheckting.models.entities.User;

public interface UserRepo
	extends JpaRepository<User,UUID>{
	User findByEmail(String email);
	User findByCode(UUID code);
	User findOneByUsernameOrEmail(String username, String email);
	
}
