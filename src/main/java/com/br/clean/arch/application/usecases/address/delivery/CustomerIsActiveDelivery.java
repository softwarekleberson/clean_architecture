package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class CustomerIsActiveDelivery {

	private RepositoryDelivery repository;
	
	public CustomerIsActiveDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery customerIsActiveDelivery(String id) {
		return this.repository.customerIsActive(id);
	}
	
}
