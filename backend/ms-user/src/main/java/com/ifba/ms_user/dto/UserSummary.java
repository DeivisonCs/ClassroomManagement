package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Student;
import com.ifba.ms_user.models.Teacher;
import com.ifba.ms_user.models.enums.OccupationType;

public record UserSummary(
	Long id,
	String nome,
	String email,
	OccupationType tipo
) {
	public UserSummary(Account usuario) {
	    this(
	        usuario.getId(),
	        usuario.getName(),
	        usuario.getEmail(),
	        usuario instanceof Student ? OccupationType.STUDENT :
	        usuario instanceof Teacher ? OccupationType.ADMIN :
	        	OccupationType.ADMIN
	    );
	}
}