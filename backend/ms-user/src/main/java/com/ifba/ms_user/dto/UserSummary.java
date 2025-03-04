package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Person;

public record UserSummary(
	String registration,
	String name,
	String email,
	String occupation
) {
	public UserSummary(Account account, Person person) {
	    this(
	        account.getRegistration(),
	        person.getName(),
	        person.getEmail(),
	        account.getOccupation().getName()
	    );
	}
}