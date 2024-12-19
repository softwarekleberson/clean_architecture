package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class CreateDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public CreateDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery createDelivery(String cpf, Delivery delivery) {
		return this.repositoryDelivery.createDelivery(cpf, delivery);
	}
}
