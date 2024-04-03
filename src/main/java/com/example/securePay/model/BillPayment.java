package com.example.securePay.model;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class BillPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;

	private String billType;
	private Double amount;
	private LocalDate paymentDate;
	@ManyToOne
	@JoinColumn(name = "walletId")
	private Wallet wallet;

	public BillPayment() {
		this.paymentDate = LocalDate.now();
	}

}
