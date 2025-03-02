package com.ifba.ms_user.dto;

import org.hibernate.validator.constraints.br.CPF;

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
	@CPF(message = "CPF should be valid")
	String cpf,
	
	@NotBlank(message = "Human readable id is required")
	@Size(max = 7, message = "Human readable id must be 7 characters")
	String humanReadableId
) {
	
}
