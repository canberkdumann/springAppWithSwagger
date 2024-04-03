package com.example.securePay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	@NotBlank
	@NotNull
	@NotEmpty
	private String name;

	@NotBlank
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String mobileNo;
	@NotBlank
	@NotNull
	@NotEmpty
	private String password;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cusid")
	private Wallet wallet;

}
