package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class DeleteDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public DeleteDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery deleteDelivery(Long id) {
		return this.repositoryDelivery.deleteDelivery(id);
	} 
}
