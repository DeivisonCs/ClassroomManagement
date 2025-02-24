package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.enums.OccupationType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserForm(
	@NotNull(message = "Occupation is required")
	OccupationType occupation,
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	String name,
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	String email,
	
	@NotBlank(message = "CPF is required")
	@Size(min = 11, max = 11, message = "CPF must be 11 characters")
	String cpf,
	
	@NotBlank(message = "Human readable id is required")
	@Size(min = 6, max = 20, message = "Human readable id must be between 6 and 20 characters")
	String humanReadableId,
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	String password

) {
	
}
