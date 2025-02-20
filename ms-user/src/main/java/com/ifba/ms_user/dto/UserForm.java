package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.enums.OccupationType;

public record UserForm(
	OccupationType occupation,
	String name,
	String email,
	String cpf,
	String enrollmentNumber,
	String teacherRegistrationNumber,
	String adminNumber
) {
	
}
