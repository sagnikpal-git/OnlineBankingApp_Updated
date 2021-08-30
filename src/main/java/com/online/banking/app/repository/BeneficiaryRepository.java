package com.online.banking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.banking.app.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
	public Beneficiary findByAccountNumber(int toAccountnumber);
	
	public void save(Float currentSourceAmount);	
}