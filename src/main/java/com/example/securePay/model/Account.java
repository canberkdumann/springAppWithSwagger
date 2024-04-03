package com.example.securePay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accId;
	private String accountNo;
	private String ifscCode;
	private String bankName;
	private Double balance;

}
