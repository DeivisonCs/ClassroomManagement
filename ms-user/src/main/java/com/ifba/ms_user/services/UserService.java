package com.ifba.ms_user.services;


import org.springframework.stereotype.Service;

import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.dto.UserSummary;
import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.factories.UserFactory;
import com.ifba.ms_user.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserSummary registerUser(UserForm form) {
		Account user = UserFactory.createUser(form);
		return new UserSummary(userRepository.save(user));
	}
	
	public UserSummary findById(Long id) {
		return new UserSummary(userRepository.findById(id).get());
	}
	
	public UserSummary updateById(Long id, UserForm form) {
        Account user = userRepository.findById(id).get();
        
        user.setName(form.name());
        user.setEmail(form.email());
        user.setCpf(form.cpf());
        
        return new UserSummary(userRepository.save(user));
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
