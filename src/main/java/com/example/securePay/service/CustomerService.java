package com.example.securePay.service;

import com.example.securePay.exception.CustomerException;
import com.example.securePay.model.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer, String key) throws CustomerException;

}
