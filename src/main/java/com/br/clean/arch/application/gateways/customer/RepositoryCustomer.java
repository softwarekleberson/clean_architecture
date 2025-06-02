package com.br.clean.arch.application.gateways.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.domain.entitie.customer.Customer;

public interface RepositoryCustomer {

	Customer save(Customer customer);
	Page<Customer> listCustomer(Pageable pageable);
	Customer updateCustomer(String id, Customer customer);
	Optional<Customer> findByCpf(String cpf);
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findById(String id);
}
