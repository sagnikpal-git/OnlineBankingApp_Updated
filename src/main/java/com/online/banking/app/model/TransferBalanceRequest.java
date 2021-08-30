package com.online.banking.app.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class TransferBalanceRequest {
	
	@Id
	@GeneratedValue
	private int transactionId;	
	
private int FromAccountNumber;
private int ToAcccountNumber;
private BigDecimal amount;
}
