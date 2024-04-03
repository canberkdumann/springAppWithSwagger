package com.example.securePay.service;

import java.util.List;
import java.util.Optional;

import com.example.securePay.exception.AccountException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.Account;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.model.Wallet;
import com.example.securePay.repository.AccountDAO;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import com.example.securePay.repository.WalletDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private SessionDAO sessionDAO;

	@Autowired
	private WalletDAO walletDAO;

	@Override
	public Account addAccount(Account account, String key) throws CustomerException, WalletException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add bank account...");
		}

		Wallet wallet = walletDAO.findById(customerDAO.findById(loggedInUser.getUserId()).get().getWallet().getWid())
				.orElseThrow(() -> new WalletException("wallet does not exist"));

		Account savedAccount = accountDAO.save(account);

		wallet.getAccounts().add(savedAccount);
		walletDAO.save(wallet);
		return savedAccount;

	}

	@Override
	public Account removeAccount(Integer accId, String key)
			throws CustomerException, AccountException, WalletException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add bank account...");
		}

		Account existingAccount = accountDAO.findById(accId)
				.orElseThrow(() -> new AccountException("No account found..."));

		accountDAO.delete(existingAccount);

		return existingAccount;

	}

	@Override
	public List<Account> viewAllAccount(Integer walletId, String key)
			throws CustomerException, WalletException, AccountException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to  add bank account...");
		}
		Wallet existingWallet = walletDAO.findById(walletId)
				.orElseThrow(() -> new WalletException("wallet not found..."));
		if (existingWallet.getAccounts().isEmpty())
			throw new AccountException("NO account found...");
		return existingWallet.getAccounts();
	}

}
