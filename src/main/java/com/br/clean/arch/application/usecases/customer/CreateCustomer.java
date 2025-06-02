package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.gateways.password.RepositoryPasswordEncoder;
import com.br.clean.arch.application.usecases.customer.dto.input.CreateCustomerCommand;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateEmailException;

public class CreateCustomer {

	private RepositoryCustomer repositoriy;
	private RepositoryPasswordEncoder passwordEncoder;
	
	public CreateCustomer(RepositoryCustomer repositoriy, RepositoryPasswordEncoder passwordEncoder) {
		this.repositoriy = repositoriy;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Customer createCustomer(CreateCustomerCommand dto) {
		
		checkPasswordEqualityandConfirmPassword(dto);
		findByCpf(dto.cpf());
		findByEmail(dto.email().getEmail());
		String hashedPassword = passwordEncoder.encode(dto.password());
		
		 Customer newCustomer = new Customer(
		            dto.cpf(),
		            dto.name(),
		            dto.birth(),
		            hashedPassword,
		            dto.gender(),
		            dto.phone(),
		            dto.email()
		        );
		
		return this.repositoriy.save(newCustomer);
	}

	private void checkPasswordEqualityandConfirmPassword(CreateCustomerCommand dto) {
		if(!dto.password().equals(dto.confirmPassword())) {
			throw new IllegalArgumentException("Password not equals confirm password");
		}
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
