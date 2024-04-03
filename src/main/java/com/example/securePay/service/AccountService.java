package com.example.securePay.service;

import com.example.securePay.exception.AccountException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.Account;

import java.util.List;

public interface AccountService {
	public Account addAccount(Account account, String key) throws CustomerException, WalletException;

	public Account removeAccount(Integer accId, String key) throws CustomerException, AccountException, WalletException;

	public List<Account> viewAllAccount(Integer walletId, String key)
			throws CustomerException, WalletException, AccountException;
}
