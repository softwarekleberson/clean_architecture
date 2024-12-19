
package com.br.clean.arch.application.usecases.customer;

import java.util.List;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class ListCustomer {

	private RepositoriyCustomer repositoriyCustomer;
	
	public ListCustomer(RepositoriyCustomer repositoriyCustomer) {
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public List<Customer> listCustomers(){
		return this.repositoriyCustomer.listCustomer();
	}
}
