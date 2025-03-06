package com.ifba.salas_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ifba.salas_service.security.filters.SecurityFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
	            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(req -> {
	            	req.requestMatchers(HttpMethod.GET, "/**").permitAll();
	            	  req.requestMatchers(HttpMethod.POST, "/**").hasAnyAuthority("TEACHER", "ADMIN");
	            	  req.requestMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority("TEACHER", "ADMIN");
	            	  req.requestMatchers(HttpMethod.PATCH, "/**").hasAnyAuthority("TEACHER", "ADMIN");
	            	  req.requestMatchers(HttpMethod.PUT, "/**").hasAnyAuthority("TEACHER", "ADMIN");
	                req.anyRequest().authenticated();
	            })
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		 return configuration.getAuthenticationManager();
    }

}
