package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class EnsuresAprimaryAddress {

	private RepositoryDelivery repositoryDelivery;

	public EnsuresAprimaryAddress(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery ensuresAprimaryAddress(String cpf, boolean main) {
		return this.repositoryDelivery.ensuresAprimaryAddress(cpf, main);
	}
}