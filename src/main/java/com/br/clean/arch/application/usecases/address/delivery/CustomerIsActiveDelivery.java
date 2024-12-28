package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class CustomerIsActiveDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public CustomerIsActiveDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery customerIsActiveDelivery(String id) {
		return this.repositoryDelivery.customerIsActive(id);
	}
	
}
