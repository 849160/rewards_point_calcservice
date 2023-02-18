package com.masupa.ems.transformer;

import java.time.YearMonth;
import java.util.Map;

import lombok.Data;

@Data
public class RewardsByMonth {
 private Long customerId;
 private Long totalRewards;
 private Map<YearMonth,MonthlyTransactions> transactions;
 
}
