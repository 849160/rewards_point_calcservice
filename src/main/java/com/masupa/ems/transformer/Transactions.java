package com.masupa.ems.transformer;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transactions {
private Long transactionId;
private BigDecimal transactionAmt;
private Long rewardPoints;
}
