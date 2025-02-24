package com.ifba.ms_user.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ifba.ms_user.repositories.AccountRepository;


@Service
public class AutenticacaoService implements UserDetailsService{

	
	private AccountRepository usuarioRepository;
	
	public AutenticacaoService(AccountRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.usuarioRepository.findByhumanReadableId(username);
	}

}
