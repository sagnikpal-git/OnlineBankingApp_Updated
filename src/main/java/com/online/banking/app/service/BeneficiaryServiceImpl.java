package com.online.banking.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.banking.app.exceptions.BeneficiaryNotFoundException;
import com.online.banking.app.model.Beneficiary;
import com.online.banking.app.repository.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	@Override
	@Transactional
	public Beneficiary createBeneficiary(Beneficiary beneficiary) {
		Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
		addBeneficiaryToCustomer(savedBeneficiary);
		return savedBeneficiary;
	}

	private void addBeneficiaryToCustomer(Beneficiary savedBeneficiary) {
		
	}

	@Override
	public Beneficiary getBeneficiaryInfo(int beneficiaryId) {
		return beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> 
		new BeneficiaryNotFoundException("Beneficiary account not available with Id:: " + beneficiaryId));
	}

	@Override
	public void deleteBeneficiary(int beneficiaryId) {
		if(!beneficiaryRepository.findById(beneficiaryId).isPresent()) {
			throw new BeneficiaryNotFoundException("Beneficiary account not available with Id:: " + beneficiaryId);
		}
		beneficiaryRepository.deleteById(beneficiaryId);
	}

	@Override
	public boolean isBeneficiaryAdded(int accountNumber) {
		return beneficiaryRepository.findByAccountNumber(accountNumber).isPresent();
	}
	
	public List<Beneficiary> GetAllBeneficiary() {
		return beneficiaryRepository.findAll();
	}
}