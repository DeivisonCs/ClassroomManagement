package com.ifba.ms_user.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher extends Account {
	@Column(unique = true)
	private String teacherRegistrationNumber;

	public Teacher(String name, String email, String cpf, String teacherRegistrationNumber) {
		this.setName(name);
		this.setEmail(email);
		this.setCpf(cpf);
		this.teacherRegistrationNumber = teacherRegistrationNumber;
	}
}

