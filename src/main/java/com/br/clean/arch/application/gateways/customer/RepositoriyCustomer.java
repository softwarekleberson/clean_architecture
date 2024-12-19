package com.br.clean.arch.application.gateways.customer;

import java.util.List;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.CustomerUpdateDto;

public interface RepositoriyCustomer {

	Customer createCustomer(Customer customer);
	List<Customer> listCustomer();
	Customer getCustomerByCpf(String cpf);
	Customer updateCustomer(String id, CustomerUpdateDto dto);
}
