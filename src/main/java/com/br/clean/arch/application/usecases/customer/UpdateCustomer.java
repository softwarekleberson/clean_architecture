package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.CustomerUpdateDto;

public class UpdateCustomer {

	private RepositoriyCustomer repositoriyCustomer;
	
	public UpdateCustomer(RepositoriyCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Customer updateCustomer(String id, CustomerUpdateDto dto) {
		if(repositoriyCustomer.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repositoriyCustomer.updateCustomer(id, dto);
	}
}
