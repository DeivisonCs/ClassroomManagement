package com.ifba.ms_user.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifba.ms_user.dto.OccupationDto;
import com.ifba.ms_user.dto.UserDetails;
import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.dto.UserSummary;
import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Occupation;
import com.ifba.ms_user.models.Person;
import com.ifba.ms_user.repositories.AccountRepository;
import com.ifba.ms_user.repositories.OccupationRepository;
import com.ifba.ms_user.repositories.PersonRepository;

@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;
	private final OccupationRepository occupationRepository;
	private final UserEventPublisher userEventPublisher;
	
	public AccountService(AccountRepository accountRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder, OccupationRepository occupationRepository, UserEventPublisher userEventPublisher) {
		this.accountRepository = accountRepository;
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
		this.occupationRepository = occupationRepository;
		this.userEventPublisher = userEventPublisher;
	}
	
	@Transactional
	public UserSummary registerAccount(UserForm form) {
		if (accountRepository.existsByRegistration(form.registration())) {
			throw new IllegalArgumentException("Registration already exists");
		}
		
		Person person = personRepository.findByCpf(form.cpf())
		        .orElseGet(() -> {
		            Person newPerson = new Person(form.name(), form.email(), form.cpf());
		            personRepository.save(newPerson);
		            return newPerson;
		        });

		String password = generateDefaultPassword(form.cpf());
		
		Occupation occupation = occupationRepository.findById(form.occupationId())
		    .orElseThrow(
				() -> new IllegalArgumentException("Occupation not found")
			);

	    Account account = new Account(
			form.registration(),
	    	passwordEncoder.encode(password), 
			occupation, 
			person
		);

	    account = accountRepository.save(account);

		if ("TEACHER".equals(occupation.getName())) {
			userEventPublisher.publishProfessorCreatedEvent(account);
		} else if ("STUDENT".equals(occupation.getName())) {
			userEventPublisher.publishStudentCreatedEvent(account);
		}
	
	   return new UserSummary(account);
    }
	
	public UserDetails findById(Long id) {
		Account account = accountRepository.findById(id).get();
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
	
	private String generateDefaultPassword(String cpf) {
        return "ifba." + cpf;
    }

	public List<OccupationDto> listOccupations() {
		List<Occupation> occupations = occupationRepository.findAll();
		
		return occupations.stream().map(OccupationDto::new).toList();
	}

	public List<UserSummary> listAll() {
		List<Account> accounts = accountRepository.findAll();
		
		return accounts.stream().map(UserSummary::new).toList();
	}

}
