package com.online.banking.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.online.banking.app.exceptions.UnableToTransferFundException;
import com.online.banking.app.model.BankAccountInfo;
import com.online.banking.app.model.Beneficiary;
import com.online.banking.app.model.Response;
import com.online.banking.app.model.TransferBalanceRequest;
import com.online.banking.app.repository.BankRepository;
import com.online.banking.app.repository.BeneficiaryRepository;

@Service
public class FundTransferringServiceImpl implements FundTransferringService {
	@Autowired
	BankRepository repo;
	
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	

	public BankAccountInfo save(BankAccountInfo accountinfo) {

		return repo.save(accountinfo);
	}

	public ResponseEntity<Response> transferBalanceRequest(TransferBalanceRequest transferBalanceRequest)
			throws UnableToTransferFundException {

		Response response = null;

		int fromaccountnumber = transferBalanceRequest.getFromAccountNumber();
		int toAccountnumber = transferBalanceRequest.getToAcccountNumber();
		BigDecimal amount = transferBalanceRequest.getAmount();
		BankAccountInfo fromAccount = repo.findByaccountNumber(fromaccountnumber);
		Beneficiary toAccount = beneficiaryRepository.findByAccountNumber(toAccountnumber);
		if (fromAccount.getAvailableBalance().compareTo(BigDecimal.ONE) == 1
				&& fromAccount.getAvailableBalance().compareTo(amount) == 1) {
			fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(amount));
			repo.save(fromAccount);
			toAccount.setAvailableBalance(toAccount.getAvailableBalance().add(amount));
			beneficiaryRepository.save(toAccount);
		
			if (fromAccount != null && toAccount != null) {
				response = new Response();
				response.setSuccessMessage("Money Transfer Successful!!!");
				response.setAccountNumber(toAccountnumber);
				response.setStatusCode(200);				
			}
			else {
			throw new UnableToTransferFundException("Money Tranfer Failed");

		}}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
		
	@Override
	public List<BankAccountInfo> getAllAccounts() {
		return repo.findAll();
	}
}
