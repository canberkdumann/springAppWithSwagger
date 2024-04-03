package com.example.securePay.repository;

import com.example.securePay.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDAO extends JpaRepository<CurrentUserSession, Integer> {
	public CurrentUserSession findByUuid(String uuid);

}
