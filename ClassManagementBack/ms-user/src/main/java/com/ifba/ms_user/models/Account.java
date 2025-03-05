package com.ifba.ms_user.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Account implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String registration;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Occupation occupation;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Person person;
	
	@ManyToMany(mappedBy = "accounts")
	private List<Subject> subjects = new ArrayList<>();
	
	public Account() {}
	
	public Account(String registration, String password, Occupation occupation, Person person) {
		this.registration = registration;
		this.password = password;
		this.occupation = occupation;
		this.person = person;
		this.enabled = true;
	}
	
	public Account(Long id, String registration, String password, Occupation occupation, Person person, List<Subject> subjects) {
		this.id = id;
		this.registration = registration;
		this.password = password;
		this.occupation = occupation;
		this.person = person;
		this.enabled = true;
		this.subjects = subjects;
	}

	public Person getPerson() {
        return person;
    }
	
	public Long getId() {
		return id;
	}
	
	public String getRegistration() {
		return registration;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Occupation getOccupation() {
		return occupation;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
	
	public void removeSubject(Subject subject) {
		this.subjects.remove(subject);
	}
	
	public void clearSubjects() {
		this.subjects.clear();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(occupation.getName()));
	}
	
	@Override
	public String getUsername() {
		return registration;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
}