package com.online.banking.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BeneficiaryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Please Enter your Full Name")
	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "Please enter valid beneficiary name")
	private String name;

	@NotBlank(message = "Please Enter Beneficiary Bank Name")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "Please enter a valid bank name")
	private String bankName;

	@NotBlank(message = "Please Enter Beneficiary Account IFSC Code")
	@Size(min = 3, max = 15)
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Please enter valid ifsCode")
	private String ifscCode;

	@NotBlank(message = "Please Enter the Branch Name")
	private String branchName;

	@Size(min = 1, max = 12)
	@NotNull(message = "Please Enter Beneficiary Account Number")
	@Pattern(regexp = "[0-9]{12}", message = "Please enter a valid account number")
	private int accountNumber;

	@NotNull(message = "Please Enter the Balance Amount")
	private BigDecimal availableBalance;

}
