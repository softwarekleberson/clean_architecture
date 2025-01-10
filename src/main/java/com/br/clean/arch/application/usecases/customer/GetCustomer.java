package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class GetCustomer {

	private RepositoriyCustomer repositoriy;
	
	public GetCustomer(RepositoriyCustomer repositoriy) {
		this.repositoriy = repositoriy;
	}
	
	public Customer getCustomerByCpf(String cpf) {
		return this.repositoriy.getCustomerByCpf(cpf);
	}
}
