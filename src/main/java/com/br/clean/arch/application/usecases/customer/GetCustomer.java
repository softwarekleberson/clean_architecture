package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class GetCustomer {

	private RepositoriyCustomer repositoriyCustomer;
	
	public GetCustomer(RepositoriyCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Customer getCustomerByCpf(String cpf) {
		return this.repositoriyCustomer.getCustomerByCpf(cpf);
	}
}
