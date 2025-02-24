package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Person;
import com.ifba.ms_user.models.enums.OccupationType;

public record UserSummary(
	Long id,
	String nome,
	String email,
	OccupationType tipo
) {
	public UserSummary(Account account, Person person) {
	    this(
	        account.getId(),
	        person.getName(),
	        person.getEmail(),
	        account.getOccupation()
	    );
	}
	
}