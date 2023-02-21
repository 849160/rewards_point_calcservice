package com.masupa.ems.transformer;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
@Data
public class TransactionRequest {
	private Long customerId;
	private BigDecimal transactionAmt;
	private LocalDate transactionDate;
}
