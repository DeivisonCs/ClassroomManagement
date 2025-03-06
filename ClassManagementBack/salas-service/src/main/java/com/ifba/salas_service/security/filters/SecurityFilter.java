package com.ifba.salas_service.security.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ifba.salas_service.services.JWTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityFilter extends OncePerRequestFilter{

	private JWTokenService tokenService;
	
	private SecurityFilter(JWTokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
				@NonNull HttpServletResponse response,
				@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("FILTRO CHAMADO");
		var token = recuperarToken(request);
		if(token!=null) {
			var role = tokenService.getRole(token);
			
			var authentication = new UsernamePasswordAuthenticationToken(
					null,
					null,
					Collections.singletonList(new SimpleGrantedAuthority(role))
	                );
			
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
