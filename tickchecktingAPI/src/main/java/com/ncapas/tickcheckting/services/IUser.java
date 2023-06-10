package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.User;

public interface IUser {
	//Crear nuevo usuario
	void save() throws Exception;
	//Eliminar usuario
	void deleteByCode(String code) throws Exception;
	//Obtener un usuario
	User findByCode(String code);
	//obtener todos los usuarios
	List<User> findAll();
	

}
