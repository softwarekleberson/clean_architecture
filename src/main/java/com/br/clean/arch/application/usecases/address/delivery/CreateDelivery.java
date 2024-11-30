package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.address.domain.entitie.address.Delivery;
import com.br.clean.arch.application.gateways.address.RepositoryDelivery;

public class CreateDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public CreateDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery createDelivery(Delivery delivery) {
		return this.repositoryDelivery.createDelivery(delivery);
	}
}
