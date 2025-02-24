package com.ifba.ms_user.services;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifba.ms_user.dto.UserDetails;
import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.dto.UserSummary;
import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Person;
import com.ifba.ms_user.repositories.PersonRepository;
import com.ifba.ms_user.repositories.AccountRepository;

@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	public AccountService(AccountRepository accountRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public UserSummary registerAccount(UserForm form) {
		logger.debug("Registering account with humanReadableId: {}", form.humanReadableId());

		if (accountRepository.existsByhumanReadableId(form.humanReadableId())) {
			throw new IllegalArgumentException("Human readable id already exists");
		}
		
		Person person = personRepository.findByCpf(form.cpf())
		        .orElseGet(() -> {
		            Person newPerson = new Person(form.name(), form.email(), form.cpf());
		            personRepository.save(newPerson);
		            return newPerson;
		        });
		
		    Account account = new Account(form.humanReadableId(),
		    		passwordEncoder.encode(form.password()), form.occupation(), person);
		    account = accountRepository.save(account);
		
	   return new UserSummary(account, person);
    }
	
	public UserDetails findById(Long id) {
		Account account = accountRepository.findById(id).get();
		Person person = account.getPerson();
		
		return new UserDetails(account);
	}
	
	@Transactional
	public UserDetails updateById(Long id, UserForm form) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found"));
	    Person person = account.getPerson();

	    if (form.name() != null) {
	    	person.setName(form.name());
	    }
		if (form.email() != null) {
			person.setEmail(form.email());
		}

	    personRepository.save(person);

	    return new UserDetails(account);
	}
	
	@Transactional
	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}

}
