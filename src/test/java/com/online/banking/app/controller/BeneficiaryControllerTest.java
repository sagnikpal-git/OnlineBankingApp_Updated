package com.online.banking.app.controller;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.banking.app.model.Beneficiary;
import com.online.banking.app.service.BeneficiaryService;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryControllerTest {

	@Mock
	BeneficiaryService beneficiaryService;

	@InjectMocks
	BeneficiaryController beneficiaryController;

	static Beneficiary beneficiary;

	@BeforeAll
	public static void setUp() {
		beneficiary = new Beneficiary();
		beneficiary.setAccountNumber(15);
		beneficiary.setBankName("SBI");
		beneficiary.setBranchName("Hyderabad");
		beneficiary.setIfscCode("sbi123");
		beneficiary.setName("Sagnik");
		beneficiary.setAvailableBalance(new BigDecimal("2000"));

	}

	@Test
	@Order(1)
	@DisplayName("Add Beneficiary: Positive Scenario")
	public void addBeneficiaryTest() {
		beneficiaryController.addBeneficiary(beneficiary);
		verify(beneficiaryService).createBeneficiary(beneficiary);
	}
	
	@Test
	@Order(2)
	@DisplayName("Get all beneficiaries: Postive Scenario")
	public void getallbeneficiaryDetailsTest() {
		beneficiaryController.getAllAccountDetails();
		verify(beneficiaryService).GetAllBeneficiary();
	}
}
