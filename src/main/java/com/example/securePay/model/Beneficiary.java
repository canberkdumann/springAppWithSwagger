package com.example.securePay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bid;
	private String name;
	private String mobileNo;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cusomerId")
	private Customer customer;
}
