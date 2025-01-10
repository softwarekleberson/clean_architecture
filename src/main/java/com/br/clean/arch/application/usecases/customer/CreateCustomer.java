package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateEmailException;

public class CreateCustomer {

	private RepositoriyCustomer repositoriy;
	
	public CreateCustomer(RepositoriyCustomer repositoriy) {
		this.repositoriy = repositoriy;
	}
	
	public Customer createCustomer(Customer customer) {
		
		findByCpf(customer.getCpf());
		findByEmail(customer.getEmail().getEmail());
		
		return this.repositoriy.createCustomer(customer);
	}
	
	private void findByEmail(String email) {
		repositoriy.findByEmail(email).ifPresent(e -> {
			throw new DuplicateEmailException("Previously registered Email: " + email);
		});
	}

	private void findByCpf(String cpf) {
		 repositoriy.findByCpf(cpf).ifPresent(e -> {
	            throw new DuplicateCpfException("Previously registered CPF: " + cpf);
	        });
	}
}
