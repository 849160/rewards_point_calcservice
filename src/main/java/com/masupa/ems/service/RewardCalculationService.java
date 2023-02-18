package com.masupa.ems.service;

import java.util.List;

import com.masupa.ems.model.CustomerTransaction;
import com.masupa.ems.transformer.RewardsByMonth;
import com.masupa.ems.transformer.TransactionRequest;

public interface RewardCalculationService {

	RewardsByMonth rewardsByCustId(Long customerId);

	List<RewardsByMonth> rewardsByAllCustomers();

	List<CustomerTransaction> saveTransaction(List<TransactionRequest> transactionRequest);

}
