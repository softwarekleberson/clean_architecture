package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class DeleteDelivery {

	private RepositoryDelivery repository;
	
	public DeleteDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery deleteDelivery(Long id) {
		return this.repository.deleteDelivery(id);
	} 
}
