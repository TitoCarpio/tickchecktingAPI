package com.ncapas.tickcheckting.services;

import java.util.List;

import com.ncapas.tickcheckting.entities.Category;

public interface ICategory {
	void save() throws Exception;
	void deleteByCode(String code) throws Exception;
	Category findByCode(String code);
	List<Category> findAll();
}
