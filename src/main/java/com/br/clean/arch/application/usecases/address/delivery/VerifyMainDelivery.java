package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class VerifyMainDelivery {

	private RepositoryDelivery repositoryDelivery;

	public VerifyMainDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery verifyMainDelivery(String cpf) {
		return this.repositoryDelivery.verifyMainDelivery(cpf);
	}
}
