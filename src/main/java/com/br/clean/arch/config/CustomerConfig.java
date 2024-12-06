package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.gateways.customer.CustomerRepositoryJpa;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class CustomerConfig {

	@Bean
	public CreateCustomer createCustomer(RepositoriyCustomer repositoriyCustomer) {
		return new CreateCustomer(repositoriyCustomer);
	}

	@Bean
	public ListCustomer listCustomer(RepositoriyCustomer repositoriyCustomer) {
		return new ListCustomer(repositoriyCustomer);
	}
	
	@Bean
	public CustomerRepositoryJpa customerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper) {
		return new CustomerRepositoryJpa(repository, mapper);
	}
	
	@Bean
	public CustomerEntityMapper customerEntityMapper() {
		return new CustomerEntityMapper();
	}
}
