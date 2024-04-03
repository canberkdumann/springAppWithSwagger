package com.example.securePay.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.securePay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {

	@Query("select w.transactions from Wallet w where w.wid=?1")
	public List<Transaction> findByWalletid(int wallet_id);

	@Query("select t from Transaction t where t.transactionDate>=?1 and t.transactionDate<=?2 ")
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to);

	public List<Transaction> findByTransactionType(String type);

}
