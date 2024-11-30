package com.br.clean.arch.application.gateways.customer;

import java.util.List;

import com.br.clean.arch.customer.domain.entitie.custommer.Customer;

public interface RepositoriyCustomer {

	Customer createCustomer(Customer customer);
	List<Customer> listCustomer();
}
