package com.example.securePay.repository;

import java.util.List;

import com.example.securePay.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryDAO extends JpaRepository<Beneficiary, Integer> {
	List<Beneficiary> findByMobileNo(String mobileNo);

}
