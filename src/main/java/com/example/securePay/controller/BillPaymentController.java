package com.example.securePay.controller;

import java.util.List;

import com.example.securePay.exception.BillPaymentException;
import com.example.securePay.exception.CustomerException;
import com.example.securePay.exception.WalletException;
import com.example.securePay.model.BillPayment;
import com.example.securePay.service.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class BillPaymentController {

	@Autowired
	private BillPaymentService billPaymentService;

	@PostMapping("/payments/bills")
	public ResponseEntity<BillPayment> addBillPaymentHandler(@RequestBody BillPayment billPayment,
															 @RequestParam(required = false) String key)
			throws BillPaymentException, CustomerException, WalletException {

		BillPayment savedBillPayment = billPaymentService.addBillPayment(billPayment, key);
		return new ResponseEntity<BillPayment>(savedBillPayment, HttpStatus.CREATED);
	}

	@GetMapping("payments/bills")
	public ResponseEntity<List<BillPayment>> viewAllBillPaymEntityHandler(@RequestParam(required = false) String key)
			throws CustomerException, WalletException, BillPaymentException {
		List<BillPayment> billPayments = billPaymentService.viewAllBillPayments(key);
		return new ResponseEntity<List<BillPayment>>(billPayments, HttpStatus.OK);
	}
}
