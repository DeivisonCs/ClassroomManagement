package com.ifba.ms_user.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Administrator extends Account {
	@Column(unique = true)
	private String adminNumber;
	
	public Administrator(String name, String email, String cpf, String adminNumber) {
		this.setName(name);
		this.setEmail(email);
		this.setCpf(cpf);
		this.adminNumber = adminNumber;
	}	
}
