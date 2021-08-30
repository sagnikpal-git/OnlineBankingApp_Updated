package com.online.banking.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.banking.app.model.CustomerRegistrationInfo;
import com.online.banking.app.service.CustomerRegistrationService;

@ExtendWith(MockitoExtension.class)
public class CustomerRegistrationControllerTest {

	@Mock
	CustomerRegistrationService customerRegistrationService;

	@InjectMocks
	CustomerRegistrationController customerRegistrationController;

	static CustomerRegistrationInfo customerRegistrationInfo;

	@BeforeAll
	public static void setUp() {
		customerRegistrationInfo = new CustomerRegistrationInfo();
		customerRegistrationInfo.setAadharNumber(Long.valueOf(314566742));
		customerRegistrationInfo.setAddress("Hyderabad");
		customerRegistrationInfo.setEmailId("kaustav@gmail.com");
		customerRegistrationInfo.setEmployerName("Amazon");
		customerRegistrationInfo.setMobileNumber(Long.valueOf(983201345));
		customerRegistrationInfo.setName("Kaustav");
		customerRegistrationInfo.setUserId(2);
	}

	@Test
	@DisplayName("Add Customer: Positive Scenario")
	public void addCustomerRegistrationInfoTest() {
		when(customerRegistrationService.CreateCustomerInfo(customerRegistrationInfo)).thenReturn(customerRegistrationInfo);
		ResponseEntity<CustomerRegistrationInfo> response = customerRegistrationController
				.CreateCustomerInfo(customerRegistrationInfo);
		verify(customerRegistrationService).CreateCustomerInfo(customerRegistrationInfo);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
