package com.online.banking.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.banking.app.model.Response;
import com.online.banking.app.model.TransferBalanceRequest;
import com.online.banking.app.service.FundTransferringService;

@ExtendWith(MockitoExtension.class)
public class FundTransferControllerTest {
	
	@Mock
	FundTransferringService fundTransferringService;
	
	@InjectMocks
	FundTransferController fundTransferController;
	
	static TransferBalanceRequest transferBalanceRequest;
	
	@BeforeAll
	public static void setUp() {
		transferBalanceRequest = new TransferBalanceRequest(1, 10, 9, new BigDecimal("1000"));
	}

	@Test
	@DisplayName("Fund Transfer Test")
	public void FundTransferTest() {
		ResponseEntity<ResponseEntity<Response>> result = fundTransferController.SendMoney(transferBalanceRequest);
		verify(fundTransferringService).transferBalanceRequest(transferBalanceRequest);
		assertEquals(transferBalanceRequest, result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
