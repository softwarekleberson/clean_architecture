package com.br.clean.arch.application.usecases.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class ListCustomer {

	private RepositoryCustomer repositoriyCustomer;
	
	public ListCustomer(RepositoryCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Page<CustomerOutputDto> listCustomers(Pageable pageable){
		Page<Customer> customers = repositoriyCustomer.listCustomer(pageable);
		return customers
						.map(customer -> new CustomerOutputDto
						(customer.getId(), customer.getCpf(),
						 customer.getName(), customer.getEmail(),
						 customer.isActive())
						);
	}
}
