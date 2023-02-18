package com.masupa.ems.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masupa.ems.dao.CustomerTransactionDAO;
import com.masupa.ems.model.CustomerTransaction;
import com.masupa.ems.service.RewardCalculationService;
import com.masupa.ems.transformer.MonthlyTransactions;
import com.masupa.ems.transformer.RewardsByMonth;
import com.masupa.ems.transformer.TransactionRequest;
import com.masupa.ems.transformer.Transactions;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateServiceImpl implements RewardCalculationService {

	private final CustomerTransactionDAO dao;


	private Long calculatePoints(BigDecimal transactionAmount) {
		Long rewardPoints = 0L;
		double slab2Amt = 0.0;
		if (transactionAmount.doubleValue() >= 100) {
			double slabAmt = transactionAmount.doubleValue() - 100;
			rewardPoints = (long) (2 * slabAmt);
			slab2Amt = transactionAmount.doubleValue() - slabAmt;
		}

		slab2Amt = slab2Amt == 0 ? transactionAmount.doubleValue() - 50 : slab2Amt - 50;

		if (transactionAmount.doubleValue() >= 50) {
			rewardPoints = rewardPoints + (long) (1 * slab2Amt);
		}
		return rewardPoints;
	}

	@Override
	public RewardsByMonth rewardsByCustId(Long customerId) {	
		List<CustomerTransaction> translist = dao.findByCustomerId(customerId);		
		 return calculateRewards(customerId,translist);
	}

	private RewardsByMonth calculateRewards(Long customerId, List<CustomerTransaction> translist) {
		RewardsByMonth response = new RewardsByMonth();
		Map<YearMonth, List<CustomerTransaction>> translistByMonth = translist.stream()
				.collect(Collectors.groupingBy(m -> YearMonth.from(m.getTransactionDate())));
		response.setCustomerId(customerId);
		Map<YearMonth, MonthlyTransactions> transactions = new HashMap<>();
		Long totalRewarPoint = 0L;
		for (Map.Entry<YearMonth, List<CustomerTransaction>> entry : translistByMonth.entrySet()) {
			MonthlyTransactions monthyTransactions = new MonthlyTransactions();
			List<Transactions> values = buildTransctionDTO(entry.getValue());
			Long rewardPoints = values.stream().mapToLong(x -> x.getRewardPoints()).sum();
			totalRewarPoint = totalRewarPoint + rewardPoints;

			monthyTransactions.setTotalRewardsPerMonth(rewardPoints);
			monthyTransactions.setTransations(values);
			transactions.put(entry.getKey(), monthyTransactions);
		}
		response.setTransactions(transactions);
		response.setTotalRewards(totalRewarPoint);
		return response;
	}

	private List<Transactions> buildTransctionDTO(List<CustomerTransaction> transactions) {
		List<Transactions> list = new ArrayList<>();
		for (CustomerTransaction custTrans : transactions) {
			Transactions dto = new Transactions();
			dto.setTransactionId(custTrans.getTransacionId());
			dto.setTransactionAmt(custTrans.getTransactionAmt());
			dto.setRewardPoints(calculatePoints(custTrans.getTransactionAmt()));
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<RewardsByMonth> rewardsByAllCustomers() {
		List<RewardsByMonth> response = new ArrayList<>();
		List<CustomerTransaction> customerTransactions = dao.findAll();	
		Map<Long, List<CustomerTransaction>> translistByCustomerId = customerTransactions.stream()
				.collect(Collectors.groupingBy(CustomerTransaction :: getCustomerId));
		for (Map.Entry<Long, List<CustomerTransaction>> entry : translistByCustomerId.entrySet()) {
			response.add(calculateRewards(entry.getKey(),entry.getValue()));
		}
		 return response;
	}

	@Override
	public List<CustomerTransaction> saveTransaction(List<TransactionRequest> transactionRequest) {
		List<CustomerTransaction> customerTransactions = new ArrayList<>();
		for(TransactionRequest transaction:transactionRequest) {
			CustomerTransaction customerTransaction = CustomerTransaction.builder().customerId(transaction.getCustomerId()).transactionAmt(transaction.getTransactionAmt()).transactionDate(LocalDate.now()).build();
			customerTransactions.add(customerTransaction);
		}
		return dao.saveAll(customerTransactions);
	}

}
