package com.ifba.ms_user.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserForm(
	@NotNull(message = "OccupationId is required")
	Long occupationId,
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	String name,
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	String email,
	
	@NotBlank(message = "CPF is required")
	@CPF(message = "CPF should be valid")
	String cpf,
	
	@NotBlank(message = "Registration is required")
	@Pattern(regexp = "^\\d{4}|\\d{7}|\\d{11}$", message = "Registration must be 4 (for admins), 7 (for teachers), or 11 (for students) digits")
	String registration
) {}
