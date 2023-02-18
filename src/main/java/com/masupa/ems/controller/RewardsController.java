package com.masupa.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masupa.ems.model.CustomerTransaction;
import com.masupa.ems.service.RewardCalculationService;
import com.masupa.ems.transformer.RewardsByMonth;
import com.masupa.ems.transformer.TransactionRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RewardsController {
	private final RewardCalculationService service;
	
	@GetMapping("/rewards")
	public List<RewardsByMonth> fetchAllCustomerRewards() {
		return service.rewardsByAllCustomers();
		
	}
	
	@GetMapping("/rewardsByCustomerId/{customerId}")
	public RewardsByMonth rewardsByCustId(@PathVariable Long customerId) {
		return service.rewardsByCustId(customerId);
		
	}
	
	@PostMapping("/saveTransaction")
	public List<CustomerTransaction> saveTransaction(@RequestBody List<TransactionRequest> transactionRequest) {
		return service.saveTransaction(transactionRequest);
		
	}

}
