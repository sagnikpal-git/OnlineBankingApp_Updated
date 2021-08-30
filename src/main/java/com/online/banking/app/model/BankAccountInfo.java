package com.online.banking.app.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class BankAccountInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull(message = "Please enter the Account Number")
	private int accountNumber;

	@NotEmpty(message = "Please enter the IFSC code")
	private String ifsccode;

	private String branchName;

	@NotNull(message = "Please Enter the Balance Amount")
	private BigDecimal availableBalance;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private CustomerRegistrationInfo customerRegistrationInfo;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransferBalanceRequest> transferBalanceRequest;
}
