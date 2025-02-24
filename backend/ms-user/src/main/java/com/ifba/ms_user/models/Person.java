package com.ifba.ms_user.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	 
	@Column(nullable = false, unique = true)
	private String email;
	 
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@OneToMany(mappedBy = "person")
	private List<Account> accounts = new ArrayList<>();
	
	public Person() {}
	
	public Person(String name, String email, String cpf) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
	}
	
	public Person(Long id, String name, String email, String cpf, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;	
        this.accounts = accounts;
    }
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
}
