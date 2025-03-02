package com.ifba.ms_user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifba.ms_user.dto.AuthDTO;
import com.ifba.ms_user.dto.TokenResponseDTO;
import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.services.JWTokenService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
     private AuthenticationManager manager;
	 @Autowired
	 private JWTokenService tokenService;
	
	@PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody  AuthDTO data) {
		var dtoAuth = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var authentication = manager.authenticate(dtoAuth);
		Account account = (Account) authentication.getPrincipal();
		
		return ResponseEntity.ok(new TokenResponseDTO(tokenService.gerarToken(account)));
    }

}
