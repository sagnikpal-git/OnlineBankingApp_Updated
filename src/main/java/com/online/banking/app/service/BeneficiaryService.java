package com.online.banking.app.service;

import java.util.List;

import com.online.banking.app.model.Beneficiary;

public interface BeneficiaryService {
	

	Beneficiary createBeneficiary(Beneficiary beneficiary);
	Beneficiary getBeneficiaryInfo(int beneficiaryId);
	void deleteBeneficiary(int beneficiaryId);

	boolean isBeneficiaryAdded(int accountNumber);
	List<Beneficiary> GetAllBeneficiary();
}
