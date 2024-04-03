package com.example.securePay.controller;

import com.example.securePay.exception.TransactionException;
import com.example.securePay.model.Transaction;
import com.example.securePay.model.TransactionDTO;
import com.example.securePay.service.TransactionService;
import javax.transaction.TransactionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/transaction")
	public ResponseEntity<TransactionDTO> addTranscation(
			@io.swagger.v3.oas.annotations.parameters.RequestBody TransactionDTO tr)
			throws TransactionalException, TransactionException {

		return new ResponseEntity<TransactionDTO>(transactionService.addTranscation(tr), HttpStatus.ACCEPTED);
	}

	@GetMapping("/transactions{id}")
	public ResponseEntity<List<Transaction>> viewTransactionByWallet(@PathVariable("id") Integer wallet_id)
			throws TransactionalException, TransactionException {

		return new ResponseEntity<>(transactionService.viewTransactionByWallet(wallet_id), HttpStatus.OK);
	}

	@GetMapping("/transaction")
	public ResponseEntity<List<Transaction>> viewTransactionByDate(@RequestParam String from, @RequestParam String to)
			throws TransactionalException, TransactionException {

		return new ResponseEntity<>(transactionService.viewTransactionByDate(from, to), HttpStatus.OK);
	}

	@GetMapping("/transaction{type}")
	public ResponseEntity<List<Transaction>> viewALLTransaction(@PathVariable("type") String type)
			throws TransactionalException, TransactionException {

		return new ResponseEntity<>(transactionService.viewALLTransaction(type), HttpStatus.OK);
	}
}
