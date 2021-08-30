package com.online.banking.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.banking.app.controller.CustomerRegistrationController;
import com.online.banking.app.dto.CustomerRegistrationInfoDto;
import com.online.banking.app.model.CustomerRegistrationInfo;
import com.online.banking.app.repository.CustomerRegistrationRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRegistrationServiceTest {
	
	@Mock
	CustomerRegistrationService customerRegistrationService;
	@Mock
	CustomerRegistrationRepository customerRegistrationRepository;
	
	@InjectMocks
	CustomerRegistrationController customerRegistrationController;	
	
	static CustomerRegistrationInfo customerRegistrationInfo;
	static CustomerRegistrationInfoDto customerRegistrationInfoDto;
	
	@BeforeAll
	public void setUp() {
		customerRegistrationInfoDto =new CustomerRegistrationInfoDto();
		
		customerRegistrationInfoDto.setAadharNumber(null);
		customerRegistrationInfoDto.setAddress(null);
		customerRegistrationInfoDto.setEmailId(null);
		customerRegistrationInfoDto.setEmployerName(null);
		customerRegistrationInfoDto.setMobileNumber(null);		
		customerRegistrationInfoDto.setName(null);
	
	}
		@Test
		@DisplayName("Add Customer: Positive Scenario")
		public void addCustomerRegistrationInfoDtoTest() {
		when(customerRegistrationService.CreateCustomerInfo(customerRegistrationInfoDto)).thenReturn(customerRegistrationInfoDto);
			ResponseEntity<?> result = customerRegistrationController.CreateCustomerInfo(customerRegistrationInfoDto);
			assertEquals(HttpStatus.OK, result.getStatusCode());
		}
		
	
}