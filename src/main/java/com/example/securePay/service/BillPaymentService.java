package com.example.securePay.service;

import com.example.securePay.exception.BillPaymentException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.BillPayment;

import java.util.List;

public interface BillPaymentService {
	public BillPayment addBillPayment(BillPayment billPayment, String key)
			throws BillPaymentException, CustomerException, WalletException;

	public List<BillPayment> viewAllBillPayments(String key)
			throws CustomerException, WalletException, BillPaymentException;

}
