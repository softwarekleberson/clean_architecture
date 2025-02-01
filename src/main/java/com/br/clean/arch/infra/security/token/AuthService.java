package com.br.clean.arch.infra.security.token;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

@Service
public class AuthService {

	 private final RepositoryCustomer repositoryCustomer;
	 private final JwtUtil jwtUtil;
	 private final PasswordEncoder passwordEncoder;

     public AuthService(RepositoryCustomer repositoryCustomer, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
	    this.repositoryCustomer = repositoryCustomer;
	    this.jwtUtil = jwtUtil;
	    this.passwordEncoder = passwordEncoder;
	 }

	 public AuthResponse login(AuthRequest request) {
	    Optional<Customer> cliente = repositoryCustomer.findByEmail(request.email().getEmail());

	    if (cliente.isPresent() && passwordEncoder.matches(request.password(), cliente.get().getPassword())) {
	        String token = jwtUtil.generateToken(cliente.get().getId(), cliente.get().getRole().name());
	        return new AuthResponse(token);
	    }
	       throw new RuntimeException("Credenciais inválidas!");
	    }
}
