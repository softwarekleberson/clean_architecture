package com.br.clean.arch.application.usecases.customer;

import java.util.Optional;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class GetCustomerById {

	private RepositoryCustomer repositoriy;
	
	public GetCustomerById(RepositoryCustomer repositoriy) {
		this.repositoriy = repositoriy;
	}
	
	public Optional<Customer> getCustomerById(String id) {
		return this.repositoriy.findById(id);
	}
}
