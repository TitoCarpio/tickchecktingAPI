package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.entities.Category;

public interface CategoryRepo 
	extends ListCrudRepository<Category, UUID>{

}
