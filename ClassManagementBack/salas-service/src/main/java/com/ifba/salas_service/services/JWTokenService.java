package com.ifba.salas_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class JWTokenService {

	@Value("${jwt.secret}")
	private String secret;
	
	public String getRole(String tokenJWT) {
        try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                                .withIssuer("ms_user")
                                .build()
                                .verify(tokenJWT)
                                .getClaim("role").asString();
                
        } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
        }
	}

}
