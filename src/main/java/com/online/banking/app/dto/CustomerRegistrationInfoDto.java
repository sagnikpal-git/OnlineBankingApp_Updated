package com.online.banking.app.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

public class CustomerRegistrationInfoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Please enter your name")
	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "Please enter your full name")
	private String name;
	
	@NotNull(message ="Please enter your phone number")
	@Pattern(regexp = "[0-9]{10}", message = "Please enter your valid 10 digit mobile number")
	private Long mobileNumber;
	
	@Email(message = "Please enter a valid email id")
	private String emailId;
	
	@NotEmpty(message = "Please enter your address")
	@Size(min = 4, max = 180, message = "Please enter your current residential address, not exceeding 180 characters")
	private String address;
	
	@NotNull(message ="Please enter your aadhar card number")
	@Pattern(regexp = "[0-9]{12}", message = "Please check your aadhar details")
	private Long aadharNumber;
	
	@NotEmpty(message = "Please Enter your company name")
	private String employerName;
	
	

}
