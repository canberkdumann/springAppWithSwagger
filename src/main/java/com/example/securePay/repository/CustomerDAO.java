package com.example.securePay.repository;

import com.example.securePay.model.Customer;
import com.example.securePay.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
	public Customer findByMobileNo(String mobileNo);

	public Customer findByWallet(Wallet wallet);
}
