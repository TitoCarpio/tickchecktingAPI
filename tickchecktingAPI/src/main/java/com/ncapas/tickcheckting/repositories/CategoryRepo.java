package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Category;

public interface CategoryRepo 
	extends ListCrudRepository<Category, UUID>{

}
