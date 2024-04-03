package com.example.securePay.service;


import com.example.securePay.exception.TransactionException;
import com.example.securePay.model.Transaction;
import com.example.securePay.model.TransactionDTO;
import javax.transaction.TransactionalException;

import java.util.List;

public interface TransactionService {

	public TransactionDTO addTranscation(TransactionDTO tean) throws TransactionalException, TransactionException;

	public List<Transaction> viewTransactionByWallet(Integer wallet_id)
			throws TransactionalException, TransactionException;

	public List<Transaction> viewTransactionByDate(String from, String to)
			throws TransactionalException, TransactionException;

	public List<Transaction> viewALLTransaction(String type) throws TransactionalException, TransactionException;

}
