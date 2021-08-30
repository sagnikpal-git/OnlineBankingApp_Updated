package com.online.banking.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.app.dto.CustomerRegistrationInfoDto;
import com.online.banking.app.exceptions.AadharNotFoundException;
import com.online.banking.app.exceptions.IdNotFoundException;
import com.online.banking.app.model.CustomerRegistrationInfo;
import com.online.banking.app.service.CustomerRegistrationService;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerRegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);
	
	@Autowired
	CustomerRegistrationService customerRegistrationService;

	@RequestMapping(value = "/get-customer/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get customer by Id", notes = "Find account details by user id")

	public ResponseEntity<CustomerRegistrationInfo> GetByUserId(@PathVariable Integer userId)
			throws IdNotFoundException {
		CustomerRegistrationInfo customerinfo = customerRegistrationService.getByuserId(userId);
		if (customerinfo == null)
			throw new IdNotFoundException("Customer not found with the given Id:: " + userId);
		logger.info("Inside the get customer by userId method");
		return ResponseEntity.status(HttpStatus.OK).body(customerinfo);
	}

	@RequestMapping(value = "/get-customerby-uidai/{aadharNumber}", method = RequestMethod.GET)
	@ApiOperation(value = "Get customer by uidai", notes = "Find account details with aadhar number")

	public ResponseEntity<CustomerRegistrationInfo> GetByAadharNumber(@PathVariable Long aadharNumber)
			throws AadharNotFoundException {

		CustomerRegistrationInfo customerinfo = customerRegistrationService.getByAadharNumber(aadharNumber);
		if (customerinfo == null)
			throw new AadharNotFoundException("Customer not found with Aadhar Number:: " + aadharNumber);
		logger.info("Inside the get customer by aadhar number method");
		return ResponseEntity.status(HttpStatus.OK).body(customerinfo);
	}

	@RequestMapping(value = "/add-customer", method = RequestMethod.POST)
	@ApiOperation(value = "Add a new account", notes = "Create a new account for customer")

	public ResponseEntity<CustomerRegistrationInfo> CreateCustomerInfo(
			@RequestBody CustomerRegistrationInfo customerregistartioninfo) {
		CustomerRegistrationInfo customerinfo = customerRegistrationService.CreateCustomerInfo(customerregistartioninfo);
		logger.info("Inside the add customer registration info method");
		return ResponseEntity.status(HttpStatus.CREATED).body(customerinfo);
	}

	@RequestMapping(value = "/get-all-customers", method = RequestMethod.GET)
	@ApiOperation(value = "get all customers", notes = "Find account details of all customers")

	public ResponseEntity<List<CustomerRegistrationInfo>> GetAllCutomers() {
		List<CustomerRegistrationInfo> customerinfo = customerRegistrationService.GetAllCustomers();
		logger.info("Inside get all customers method");
		return ResponseEntity.status(HttpStatus.OK).body(customerinfo);
	}

	@RequestMapping(value = "/update-customer/{userId}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update customer details", notes = "Update customer information with user id in database")

	public ResponseEntity<String> UpdateCustomerInfo(@PathVariable Integer userId,
			@RequestBody CustomerRegistrationInfo user) throws IdNotFoundException {

		CustomerRegistrationInfo customerinfo = customerRegistrationService.getByuserId(userId);
		if (customerinfo.getUserId() == null)
			throw new IdNotFoundException("Customer account not found with userid:: " + userId);

		customerinfo.setAddress(user.getAddress());
		customerinfo.setAadharNumber(user.getAadharNumber());
		customerinfo.setBankAccountInfo(user.getBankAccountInfo());
		customerinfo.setEmailId(user.getEmailId());
		customerinfo.setMobileNumber(user.getMobileNumber());
		customerinfo.setName(user.getName());
		CustomerRegistrationInfo updatedetails = customerRegistrationService.UpdateCustomerInfo(customerinfo);
		logger.info("Inside the update customers by userId method");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/delete-customer-account/{userId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete customer and related accounts", notes = "Delete customer and account details associated with him")

	public HttpStatus deleteProduct(@PathVariable Integer userId) {
		customerRegistrationService.DeleteCustomerInfo(userId);
		logger.info("Inside delete customer account by userId method");
		return HttpStatus.OK;
	}
	
	public ResponseEntity<?> CreateCustomerInfo(CustomerRegistrationInfoDto customerRegistrationInfoDto) {
		CustomerRegistrationInfoDto dto =  customerRegistrationService.CreateCustomerInfo(customerRegistrationInfoDto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
				}
}
