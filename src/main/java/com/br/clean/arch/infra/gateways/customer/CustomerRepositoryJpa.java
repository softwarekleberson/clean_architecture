package com.br.clean.arch.infra.gateways.customer;

import java.util.List;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class CustomerRepositoryJpa implements RepositoriyCustomer{

	private final CustomerRepository repository;
	private final CustomerEntityMapper mapper;
	
	public CustomerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		CustomerEntity entity = mapper.toEntity(customer);
		repository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public List<Customer> listCustomer() {
		return repository.findAll().stream()
			   .map(mapper::toDomain)
			   .collect(Collectors.toList());
	}

	@Override
	public Customer getCustomerByCpf(String cpf) {
		CustomerEntity entity = repository.findByCpf(cpf);
		if(entity == null) {
			throw new IllegalArgumentException("Cpf not found");
		}
		return mapper.toDomain(entity);
	}
}
