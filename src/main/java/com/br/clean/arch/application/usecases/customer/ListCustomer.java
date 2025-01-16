
package com.br.clean.arch.application.usecases.customer;

import java.util.List;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class ListCustomer {

	private RepositoryCustomer repositoriyCustomer;
	
	public ListCustomer(RepositoryCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public List<Customer> listCustomers(){
		return this.repositoriyCustomer.listCustomer();
	}
}
