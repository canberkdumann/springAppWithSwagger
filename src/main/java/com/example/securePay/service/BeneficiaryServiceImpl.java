package com.example.securePay.service;

import java.util.List;

import com.example.securePay.exception.BeneficiaryException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.model.Beneficiary;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.model.Customer;
import com.example.securePay.repository.BeneficiaryDAO;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private SessionDAO sessionDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private BeneficiaryDAO beneficiaryDAO;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException {

		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Customer existingCustomer = customerDAO.findById(beneficiary.getCustomer().getCid())
				.orElseThrow(() -> new CustomerException("customer not found..create account"));
		beneficiary.setCustomer(existingCustomer);
		return beneficiaryDAO.save(beneficiary);

	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary beneficiary, String key)
			throws CustomerException, BeneficiaryException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		Beneficiary existingBeneficiary = beneficiaryDAO.findById(beneficiary.getBid())
				.orElseThrow(() -> new BeneficiaryException("enter current beneficiary details"));

		if (existingBeneficiary.getCustomer().getCid() != loggedInUser.getUserId())
			throw new CustomerException("customer not found...");

		beneficiaryDAO.delete(existingBeneficiary);

		return existingBeneficiary;
	}

	@Override
	public List<Beneficiary> viewBeneficiaries(String mobileNo, String key)
			throws CustomerException, BeneficiaryException {

		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add beneficiary...");
		}

		List<Beneficiary> beneficiaries = beneficiaryDAO.findByMobileNo(mobileNo);
		if (beneficiaries.isEmpty())
			throw new BeneficiaryException("NO beneficiary found....");

		return beneficiaries;
	}

}
