package com.br.clean.arch.infra.controller.customer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final ListCustomer listCustomer;
	
	public CustomerController(CreateCustomer createCustomer, ListCustomer listCustomer) {
		this.createCustomer = createCustomer;
		this.listCustomer = listCustomer;
	}
	
	public CustomerListDto createUser(@RequestBody CustomerDto dto) {
		Customer customer = createCustomer.createCustomer(new Customer(dto.cpf(), dto.name(), dto.birth(), dto.gender(), dto.phone(), dto.email()));
		return new CustomerListDto(customer.getCpf(), customer.getCpf(), customer.getEmail());
	}
}
