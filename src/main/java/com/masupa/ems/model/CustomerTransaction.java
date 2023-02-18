package com.masupa.ems.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_transaction")
public class CustomerTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transacionId;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "trans_date" , columnDefinition = "DATE")
	private LocalDate  transactionDate;
	
	@Column(name = "trans_amount")
	private BigDecimal transactionAmt;

}
