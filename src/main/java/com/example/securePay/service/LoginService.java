package com.example.securePay.service;


import com.example.securePay.exception.LoginException;
import com.example.securePay.model.LoginDTO;

public interface LoginService {

	public String logIntoAccount(LoginDTO dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;

}
