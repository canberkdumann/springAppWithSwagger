package com.example.securePay.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.securePay.exception.LoginException;
import com.example.securePay.model.CurrentUserSession;
import com.example.securePay.model.Customer;
import com.example.securePay.model.LoginDTO;
import com.example.securePay.repository.CustomerDAO;
import com.example.securePay.repository.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerDAO cDao;

	@Autowired
	private SessionDAO sDao;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {

		Customer existingCustomer = cDao.findByMobileNo(dto.getMobileNo());

		if (existingCustomer == null) {

			throw new LoginException("Please Enter a valid mobile number");

		}

		Optional<CurrentUserSession> validCustomerSessionOpt = sDao.findById(existingCustomer.getCid());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this number");

		}

		if (existingCustomer.getPassword().equals(dto.getPassword())) {

			String key = RandomString.make(6);

			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCid(), key,
					LocalDateTime.now());

			sDao.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {

		CurrentUserSession validCustomerSession = sDao.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this number");

		}

		sDao.delete(validCustomerSession);

		return "Logged Out !";

	}

}
