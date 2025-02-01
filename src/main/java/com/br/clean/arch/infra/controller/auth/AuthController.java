package com.br.clean.arch.infra.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.password.AuthenticateUser;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.CustomerDto;
import com.br.clean.arch.infra.security.token.AuthRequest;
import com.br.clean.arch.infra.security.token.AuthResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final CreateCustomer createCustomer;
	private final AuthenticateUser authenticateUser;

	public AuthController(CreateCustomer createCustomer, AuthenticateUser authenticateUser) {
		this.createCustomer = createCustomer;
		this.authenticateUser = authenticateUser;
	}
	
	@PostMapping("/register/customer")
	public ResponseEntity<AuthResponse> createCustomer(@RequestBody @Valid CustomerDto dto) {
	    Customer customer = createCustomer.createCustomer(
	        new Customer(
	            dto.cpf(), 
	            dto.name(), 
	            dto.birth(), 
	            dto.password(), 
	            dto.confirmPassword(), 
	            dto.gender(), 
	            dto.phone(), 
	            dto.email()
	        )
	    );
	    
	    String token = authenticateUser.authenticate(dto.email().getEmail(), dto.password());	    
	    return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/login/customer")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
	    String token = authenticateUser.authenticate(request.email().getEmail(), request.password());
	    return ResponseEntity.ok(new AuthResponse(token));
	}
}
