package com.br.clean.arch.application.usecases.customer;

import java.util.Optional;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;

public class GetCustomer {

	private RepositoryCustomer repositoriy;
	
	public GetCustomer(RepositoryCustomer repositoriy) {
		this.repositoriy = repositoriy;
	}
	
	public Optional<CustomerOutputDto> getCustomerByCpf(String cpf) {
		 return this.repositoriy.findByCpf(cpf) 
                 .map(customer -> new CustomerOutputDto(
                     customer.getId(),
                     customer.getCpf(),
                     customer.getName(),
                     customer.getEmail(),
                     customer.isActive()
                 ));
	}
}
