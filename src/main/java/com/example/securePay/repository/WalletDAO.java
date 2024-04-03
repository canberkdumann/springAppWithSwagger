package com.example.securePay.repository;

import com.example.securePay.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDAO extends JpaRepository<Wallet, Integer> {

}
