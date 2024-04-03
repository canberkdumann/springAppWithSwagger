package com.example.securePay.service;


import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.Customer;

public interface WalletService {
	public Customer createAccount(String name, String mobile, Double balance, String key)
			throws CustomerException, WalletException;

	public Customer showBalance(String mobileNo, String key) throws CustomerException;

	public String fundTransfer(String sourceMobileno, String targetMobileNo, Double amount, String key)
			throws CustomerException, WalletException;

	public Customer depositAmount(String mobileNo, Double amount, String key) throws CustomerException;

}
