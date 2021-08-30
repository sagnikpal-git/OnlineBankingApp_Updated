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

import com.online.banking.app.exceptions.BeneficiaryNotFoundException;
import com.online.banking.app.exceptions.ExistingBeneficiaryException;
import com.online.banking.app.model.Beneficiary;
import com.online.banking.app.service.BeneficiaryService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BeneficiaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@RequestMapping(value = "/add-beneficiary", method = RequestMethod.POST)
	@ApiOperation(value = "Add a new beneficiary", notes = "Create a new beneficiary account")

	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary request) {
		if(beneficiaryService.isBeneficiaryAdded(request.getAccountNumber())) {
			throw new ExistingBeneficiaryException("Beneficiary already added with given Account Number:: " 
		+ request.getAccountNumber());
		}
		Beneficiary response = beneficiaryService.createBeneficiary(request);
		logger.info("Inside add beneficiary account");
		return new ResponseEntity<Beneficiary>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete-beneficiary/{id}",method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete beneficiary account", notes = "Delete beneficiary account with beneficiary id")

	public HttpStatus deleteBeneficiary(@PathVariable Integer id) {
		beneficiaryService.deleteBeneficiary(id);
		logger.info("Inside delete beneficiary by id method");
		return HttpStatus.ACCEPTED;
	}
	
	@RequestMapping(value = "/beneficiaries", method = RequestMethod.GET)
	@ApiOperation(value = "Get beneficiary details", notes = "Find account details of all beneficiaries")
	public ResponseEntity<List<Beneficiary>> getAllAccountDetails() throws BeneficiaryNotFoundException {
		List<Beneficiary> beneficiaryinfo = beneficiaryService.GetAllBeneficiary();
		if (beneficiaryinfo == null)
			throw new BeneficiaryNotFoundException
					("Beneficiary details not found with account number:: " + beneficiaryinfo);

		logger.info("Inside the get all beneficiary account details");
		return ResponseEntity.status(HttpStatus.OK).body(beneficiaryinfo);
	}
	
}
