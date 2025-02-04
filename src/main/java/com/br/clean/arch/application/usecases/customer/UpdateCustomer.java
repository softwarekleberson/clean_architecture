package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.input.CustomerUpdateDto;

public class UpdateCustomer {

	private RepositoryCustomer repositoriyCustomer;
	
	public UpdateCustomer(RepositoryCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Customer updateCustomer(String id, CustomerUpdateDto dto) {
		if(repositoriyCustomer.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repositoriyCustomer.updateCustomer(id, dto);
	}
}
