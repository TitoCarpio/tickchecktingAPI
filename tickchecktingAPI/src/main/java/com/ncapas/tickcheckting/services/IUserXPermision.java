package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.models.entities.UserXPermision;

public interface IUserXPermision {
	void save() throws Exception;
	List<UserXPermision> findUserPermision();

}
