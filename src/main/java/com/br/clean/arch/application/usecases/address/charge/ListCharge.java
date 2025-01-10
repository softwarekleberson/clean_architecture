package com.br.clean.arch.application.usecases.address.charge;

import java.util.List;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class ListCharge {

	private RepositoryCharge repository;
	private RepositoriyCustomer repositoriyCustomer;
	
	public ListCharge(RepositoryCharge repository, RepositoriyCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public List<Charge> listCharge(String customerId) {
		if(repositoriyCustomer.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repository.listCharge(customerId);
	}
}
