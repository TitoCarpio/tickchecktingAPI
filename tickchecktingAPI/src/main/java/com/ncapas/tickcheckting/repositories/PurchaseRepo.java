package com.ncapas.tickcheckting.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Purchase;


public interface PurchaseRepo extends ListCrudRepository<Purchase, UUID> {
	List<Purchase> findByUserCode(UUID user_id);
//	List<Purchase> findByUserCodeOrderByActdatDesc(UUID user);

}
