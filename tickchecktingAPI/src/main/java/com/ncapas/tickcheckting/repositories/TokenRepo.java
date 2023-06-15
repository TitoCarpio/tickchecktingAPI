package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Token;
import com.ncapas.tickcheckting.models.entities.User;

public interface TokenRepo extends ListCrudRepository<Token, UUID> {

	List<Token> findByUserAndActive(User user, Boolean active);

}
