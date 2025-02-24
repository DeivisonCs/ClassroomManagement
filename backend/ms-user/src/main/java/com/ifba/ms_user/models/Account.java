package com.ifba.ms_user.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ifba.ms_user.models.enums.OccupationType;

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
	private String humanReadableId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private OccupationType occupation;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@ManyToOne
	private Person person;
	
	@ManyToMany(mappedBy = "accounts")
	private List<Subject> subjects = new ArrayList<>();
	
	public Account() {}
	
	public Account(String humanReadableId, String password, OccupationType occupation, Person person) {
		this.humanReadableId = humanReadableId;
		this.password = password;
		this.occupation = occupation;
		this.person = person;
		this.enabled = true;
	}
	
	public Account(Long id, String humanReadableId, String password, OccupationType occupation, Person person, List<Subject> subjects) {
		this.id = id;
		this.humanReadableId = humanReadableId;
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
	
	public String getHumanReadableId() {
		return humanReadableId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public OccupationType getOccupation() {
		return occupation;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
	public void setHumanReadableId(String humanReadableId) {
		this.humanReadableId = humanReadableId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setOccupation(OccupationType occupation) {
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
		return Collections.singletonList(new SimpleGrantedAuthority(occupation.name()));
	}
	
	@Override
	public String getUsername() {
		return humanReadableId;
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