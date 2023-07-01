package com.ncapas.tickcheckting.services;

import org.springframework.data.domain.Page;

import com.ncapas.tickcheckting.models.dtos.SaveUserDTO;
import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;

public interface IUser {
	// Crear nuevo usuario
	void save(SaveUserDTO info) throws Exception;

	
	User findOneByIdentifier(String identifier);

	User findOneByUsernameOrEmail(String username, String email);

	Boolean comparePassword(String toCompare, String current);

	// busca el usuario autentificado
	User findUserAuthenticated();

	// metodos para el uso de token
	Token registerToken(User user) throws Exception;

	Boolean isTokenValid(User user, String token);

	void cleanTokens(User user) throws Exception;

	void activeUser(User username) throws Exception;
	
	Page<User> findAll(int page, int size);
	

}
