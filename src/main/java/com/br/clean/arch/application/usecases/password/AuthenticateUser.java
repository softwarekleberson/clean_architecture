package com.br.clean.arch.application.usecases.password;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.gateways.password.RepositoryPasswordEncoder;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.AuthenticationException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;
import com.br.clean.arch.infra.security.token.JwtUtil;

public class AuthenticateUser {

    private final RepositoryCustomer repositoryCustomer;
    private final RepositoryPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; 

    public AuthenticateUser(RepositoryCustomer repositoryCustomer, RepositoryPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repositoryCustomer = repositoryCustomer;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String email, String password) {
        Customer customer = repositoryCustomer
            .findByEmail(email)
            .orElseThrow(() -> new IncorretEmailException("Email not found: " + email));
                
        if (customer.getPassword() == null || !passwordEncoder.matches(password, customer.getPassword())) {
            throw new AuthenticationException("The email or password provided is incorrect.");
        }

        return jwtUtil.generateToken(customer.getId(), "CUSTOMER"); 
    }
}
