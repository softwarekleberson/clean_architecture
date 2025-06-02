package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class GetCustomerById {

	private RepositoryCustomer repositoriy;
	
	public GetCustomerById(RepositoryCustomer repositoriy) {
		this.repositoriy = repositoriy;
	}
	
	public CustomerOutputDto getCustomerById(String id) {
		return this.repositoriy.findById(id)
				.map(customer -> new CustomerOutputDto(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail(), customer.isActive()))
				.orElseThrow(() -> new CustomerNotFoundException("Cliente com ID " + id + " não encontrado."));
	}
}