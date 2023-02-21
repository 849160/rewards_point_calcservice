package com.masupa.ems.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masupa.ems.model.CustomerTransaction;

@Repository
public interface CustomerTransactionDAO extends JpaRepository<CustomerTransaction, Long> {
	@Query("select cs from CustomerTransaction cs where cs.customerId =:custId and cs.transactionDate >= :threeMonthsAgoDate")
	List<CustomerTransaction> findByCustomerId(Long custId, LocalDate threeMonthsAgoDate);
	
	@Query("select cs from CustomerTransaction cs where cs.transactionDate >= :threeMonthsAgoDate")
	List<CustomerTransaction> findAllTransaction(LocalDate threeMonthsAgoDate);

}
