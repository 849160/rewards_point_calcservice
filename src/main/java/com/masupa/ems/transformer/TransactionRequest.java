package com.masupa.ems.transformer;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class TransactionRequest {
	private Long customerId;
	private BigDecimal transactionAmt;
}
