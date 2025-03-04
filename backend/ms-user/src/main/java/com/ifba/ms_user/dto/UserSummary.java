package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.Account;

public record UserSummary(
	Long id,
	String registration,
	String name,
	String email,
	String occupation
) {
	public UserSummary(Account account) {
	    this(
	    	account.getId(),
	        account.getRegistration(),
	        account.getPerson().getName(),
	        account.getPerson().getEmail(),
	        account.getOccupation().getName()
	    );
	}
}