package com.online.banking.app.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Beneficiary {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please Enter your Full Name")
	private String name;
	
	private String bankName;
	
	@NotBlank(message = "Please Enter the IFSC Code")
	private String ifscCode;
	
	@NotBlank(message = "Please Enter the Branch Name")
	private String branchName;
	
	@NotNull(message = "Please Enter the Account Number")
	private int accountNumber;	
	
	@NotNull(message = "Please Enter the Balance Amount")
	private BigDecimal availableBalance;	

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private CustomerRegistrationInfo customerRegistrationInfo;

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}