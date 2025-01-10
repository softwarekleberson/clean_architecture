package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class EnsuresAprimaryDelivery {

	private RepositoryDelivery repository;

	public EnsuresAprimaryDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery ensuresAprimaryAddress(String cpf, boolean main) {
		return this.repository.ensuresAprimaryAddress(cpf, main);
	}
}
