package com.ifba.ms_user.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Student extends Account {
	@Column(unique = true)
	private String enrollmentNumber;

	public Student(String name, String email, String cpf, String enrollmentNumber) {
        this.setName(name);
        this.setEmail(email);
        this.setCpf(cpf);
        this.enrollmentNumber = enrollmentNumber;
    }

	public Student() {}
	
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}
}