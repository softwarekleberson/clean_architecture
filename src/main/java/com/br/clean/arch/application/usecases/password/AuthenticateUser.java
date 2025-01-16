package com.br.clean.arch.application.usecases.password;

import java.util.Optional;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.gateways.password.RepositoryPasswordEncoder;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.AuthenticationException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;

public class AuthenticateUser {

    private final RepositoryCustomer repositoryCustomer;
    private final RepositoryPasswordEncoder passwordEncoder;

    public AuthenticateUser(RepositoryCustomer repositoryCustomer, RepositoryPasswordEncoder passwordEncoder) {
        this.repositoryCustomer = repositoryCustomer;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer authenticate(String email, String rawpassword) {
        Customer customer = repositoryCustomer
            .findByEmail(email)
            .orElseThrow(() -> new IncorretEmailException("Email not found: " + email));
                
        if (customer.getPassword() == null || !passwordEncoder.matches(rawpassword, customer.getPassword())) {
            throw new AuthenticationException("The email or password provided is incorrect.");
        }
        
        return customer;
    }
}
