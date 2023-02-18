package com.masupa.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masupa.ems.model.CustomerTransaction;

@Repository
public interface CustomerTransactionDAO extends JpaRepository<CustomerTransaction, Long> {
	
	List<CustomerTransaction> findByCustomerId(Long custId);

}
