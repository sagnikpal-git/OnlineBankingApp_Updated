package com.online.banking.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.app.exceptions.UnableToTransferFundException;
import com.online.banking.app.model.BankAccountInfo;
import com.online.banking.app.model.Response;
import com.online.banking.app.model.TransferBalanceRequest;
import com.online.banking.app.service.FundTransferringService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FundTransferController {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTransferController.class);

	@Autowired
	FundTransferringService fundTransferringService;

	@RequestMapping(value = "/get-all-bank-details", method = RequestMethod.GET)
	@ApiOperation(value = "Get all bank account details", notes = "Get all account details in bank database")

	public ResponseEntity<List<BankAccountInfo>> getAllAccountDetails() {
		List<BankAccountInfo> getaccounts = fundTransferringService.getAllAccounts();
		logger.info("Inside get all bank account details method");
		return ResponseEntity.status(HttpStatus.OK).body(getaccounts);
	}

	@PostMapping("/fundtransfer")
	@ApiOperation(value = "Transfer funds between accounts", notes = "Send money to beneficiary account")
	public ResponseEntity<ResponseEntity<Response>> SendMoney(
			@RequestBody TransferBalanceRequest transferBalanceRequest) throws UnableToTransferFundException {

		ResponseEntity<Response> transfer = fundTransferringService.transferBalanceRequest(transferBalanceRequest);
		logger.info("Inside fund transfer method");
		return ResponseEntity.status(HttpStatus.OK).body(transfer);
	}
}
