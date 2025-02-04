package com.br.clean.arch.application.gateways.customer;

import java.util.List;
import java.util.Optional;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.input.CustomerUpdateDto;

public interface RepositoryCustomer {

	Customer createCustomer(Customer customer);
	List<Customer> listCustomer();
	Customer getCustomerByCpf(String cpf);
	Customer updateCustomer(String id, CustomerUpdateDto dto);
	Optional<Customer> findByCpf(String cpf);
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findById(String id);
}
