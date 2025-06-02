package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.dto.input.UpdateCustomerCommand;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class UpdateCustomer {

	private RepositoryCustomer repositoriyCustomer;
	
	public UpdateCustomer(RepositoryCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public CustomerOutputDto updateCustomer(String id, UpdateCustomerCommand dto) {
		 Customer customer = repositoriyCustomer.findById(id)
         .orElseThrow(() -> new CustomerNotFoundException
         ("Customer not found with ID: " + id));
		 
		 customer.updateDetails(
				 dto.name(),
				 dto.birth(),
				 (dto.phone() != null ? dto.phone().getDdd() : null),
				 (dto.phone() != null ? dto.phone().getPhone() : null)
		 );
		 
		Customer savedCustomer = this.repositoriyCustomer.save(customer);
		return new CustomerOutputDto(
					savedCustomer.getId(),
					savedCustomer.getCpf(),
					savedCustomer.getName(),
					savedCustomer.getEmail(),
					savedCustomer.isActive()
				);
	}
}