package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.UserXPermision;

public interface IUserXPermision {
	void save() throws Exception;
	List<UserXPermision> findUserPermision();

}
