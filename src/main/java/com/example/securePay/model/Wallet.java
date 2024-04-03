package com.example.securePay.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer wid;
	private Double balance;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<Transaction> transactions = new ArrayList<>();

}
