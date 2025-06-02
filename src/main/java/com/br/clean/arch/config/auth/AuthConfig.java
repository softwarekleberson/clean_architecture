package com.br.clean.arch.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.gateways.password.RepositoryPasswordEncoder;
import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.password.AuthenticateUser;
import com.br.clean.arch.infra.security.token.JwtUtil;

@Configuration
public class AuthConfig {

	@Bean
	public CreateCustomer createCustomer(RepositoryCustomer repositoriyCustomer, RepositoryPasswordEncoder passwordEncoder) {
		return new CreateCustomer(repositoriyCustomer, passwordEncoder);
	}
	
	@Bean
	public AuthenticateUser authenticate(RepositoryCustomer repositoriyCustomer, RepositoryPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		return new AuthenticateUser(repositoriyCustomer, passwordEncoder, jwtUtil);
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
	    return new InMemoryUserDetailsManager();
	}
}
