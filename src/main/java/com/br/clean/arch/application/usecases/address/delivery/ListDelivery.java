package com.br.clean.arch.application.usecases.address.delivery;

import java.util.List;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class ListDelivery {

	private RepositoryDelivery repository;
	private RepositoriyCustomer repositoriyCustomer;
	
	public ListDelivery(RepositoryDelivery repository, RepositoriyCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public List<Delivery> listDelivery(String customerId) {
		if(repositoriyCustomer.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repository.listDelivery(customerId);
	}
}
