package com.ifba.ms_user.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ifba.ms_user.repositories.AccountRepository;
import com.ifba.ms_user.services.JWTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	private JWTokenService tokenService;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
				HttpServletResponse response,
				FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("FILTRO CHAMADO");
		var token = recuperarToken(request);
		if(token!=null) {
			var login = tokenService.getSubject(token);
			var usuario = accountRepository.findByRegistration(login);
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			System.out.println("Autenticado");
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	
		filterChain.doFilter(request, response);
	}
	
	public String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        System.out.println("Verificadno token");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        System.out.println("Token recuperado");
        return token.replace("Bearer ", "");
    }


}
