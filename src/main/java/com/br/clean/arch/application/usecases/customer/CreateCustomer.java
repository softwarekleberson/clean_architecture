package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class CreateCustomer {

	private RepositoriyCustomer repositoriyCustomer;
	
	public CreateCustomer(RepositoriyCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Customer createCustomer(Customer customer) {
		return this.repositoriyCustomer.createCustomer(customer);
	}
}
