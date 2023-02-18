package com.masupa.ems.transformer;

import java.util.List;

import lombok.Data;

@Data
public class MonthlyTransactions {
	private Long totalRewardsPerMonth;
	private List<Transactions> transations;
}
