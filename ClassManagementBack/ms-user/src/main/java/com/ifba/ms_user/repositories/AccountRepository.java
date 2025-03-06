package com.ifba.ms_user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ifba.ms_user.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	boolean existsByRegistration(String registration);

	public UserDetails findByRegistration(String registration);
}