package com.example.securePay.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.model.Customer;
import com.example.securePay.model.Wallet;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import com.example.securePay.repository.WalletDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Return;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private WalletDAO walletDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Customer createAccount(String name, String mobile, Double balance, String key)
			throws CustomerException, WalletException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}

		Customer customer = customerDAO.findByMobileNo(mobile);
		if (customer == null)
			throw new CustomerException("customer not found..");

		if (customer.getWallet() != null) {
			throw new WalletException("Wallet already exist..");
		}

		Wallet wallet = new Wallet();
		wallet.setBalance(balance);

		customer.setWallet(wallet);
		customerDAO.save(customer);

		return customer;
	}

	@Override
	public Customer showBalance(String mobileNo, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}

		Customer customer = customerDAO.findByMobileNo(mobileNo);

		if (customer == null) {
			throw new CustomerException("customer not found...");
		}
		return customer;

	}

	@Override
	public String fundTransfer(String sourceMobileno, String targetMobileNo, Double amount, String key)
			throws CustomerException, WalletException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}

		Customer sourceCustomer = customerDAO.findByMobileNo(sourceMobileno);
		if (sourceCustomer == null)
			throw new CustomerException("Please provide a valid  mobile no");

		Customer targetCustomer = customerDAO.findByMobileNo(targetMobileNo);
		if (targetCustomer == null)
			throw new CustomerException("Please provide a valid  mobile no");

		if (amount < sourceCustomer.getWallet().getBalance()) {
			sourceCustomer.getWallet().setBalance(sourceCustomer.getWallet().getBalance() - amount);
			targetCustomer.getWallet().setBalance(targetCustomer.getWallet().getBalance() + amount);

			customerDAO.save(targetCustomer);
			customerDAO.save(sourceCustomer);

			return "transaction sucessfully done...";
		} else
			throw new WalletException("amount not valid....");

	}

	@Override
	public Customer depositAmount(String mobileNo, Double amount, String key) throws CustomerException {

		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key");
		}

		Customer customer = customerDAO.findByMobileNo(mobileNo);
		if (customer == null)
			throw new CustomerException("Please provide a valid  mobile no");

		customer.getWallet().setBalance(customer.getWallet().getBalance() + amount);
		customerDAO.save(customer);
		return customer;

	}

}
