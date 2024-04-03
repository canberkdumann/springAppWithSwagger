package com.example.securePay.repository;

import java.util.List;

import com.example.securePay.model.BillPayment;
import com.example.securePay.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {

	public List<BillPayment> findByWallet(Wallet wallet);

}
