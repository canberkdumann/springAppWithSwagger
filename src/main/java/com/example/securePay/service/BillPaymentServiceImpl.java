package com.example.securePay.service;

import java.util.List;
import java.util.Optional;

import com.example.securePay.exception.BillPaymentException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.BillPayment;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.repository.BillPaymentDAO;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import com.example.securePay.repository.WalletDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private BillPaymentDAO billPaymentDAO;

	@Autowired
	private SessionDAO sessionDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private WalletDAO walletDAO;

	@Override
	public BillPayment addBillPayment(BillPayment billPayment, String key)
			throws BillPaymentException, CustomerException, WalletException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please Login first...");
		}

		walletDAO.findById(billPayment.getWallet().getWid())
				.orElseThrow(() -> new WalletException("wallet does not exist..."));
		return billPaymentDAO.save(billPayment);
	}

	@Override
	public List<BillPayment> viewAllBillPayments(String key)
			throws CustomerException, WalletException, BillPaymentException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key ");
		}

		customerDAO.findById(loggedInUser.getUserId()).get().getWallet();

		List<BillPayment> billPayments = billPaymentDAO
				.findByWallet(customerDAO.findById(loggedInUser.getUserId()).get().getWallet());

		return billPayments;

	}

}
