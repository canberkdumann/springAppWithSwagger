package com.example.securePay.service;

import com.example.securePay.exception.CustomerException;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.model.Customer;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {

		Customer existingCustomer = customerDAO.findByMobileNo(customer.getMobileNo());

		if (existingCustomer != null)
			throw new CustomerException("Customer Already Registered with Mobile number");

		return customerDAO.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sessionDAO.findByUuid(key);

		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}

		if (customer.getCid() == loggedInUser.getUserId()) {
			// If LoggedInUser id is same as the id of supplied Customer which we want to
			// update
			return customerDAO.save(customer);
		} else
			throw new CustomerException("Invalid Customer Details, please login first");

	}

}
