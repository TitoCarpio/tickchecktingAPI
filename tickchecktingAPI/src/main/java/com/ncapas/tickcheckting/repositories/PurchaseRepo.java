package com.ncapas.tickcheckting.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import com.ncapas.tickcheckting.models.entities.Purchase;


public interface PurchaseRepo extends ListCrudRepository<Purchase, UUID> {
	Page<Purchase> findByUserCode(UUID user_id, Pageable pageable);
//	List<Purchase> findByUserCode(UUID user_id);
//	List<Purchase> findByUserCodeOrderByActdatDesc(UUID user);

}
