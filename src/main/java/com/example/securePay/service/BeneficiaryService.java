package com.example.securePay.service;

import com.example.securePay.exception.BeneficiaryException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.model.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException;

	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, String key)
			throws CustomerException, BeneficiaryException;

	public List<Beneficiary> viewBeneficiaries(String mobileNo, String key)
			throws CustomerException, BeneficiaryException;
}
