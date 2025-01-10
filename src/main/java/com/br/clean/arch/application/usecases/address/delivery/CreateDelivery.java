package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class CreateDelivery {

	private RepositoryDelivery repository;
	
	public CreateDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery createDelivery(String cpf, Delivery delivery) {
		if(repository.findByCpf(cpf).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repository.createDelivery(cpf, delivery);
	}
}
