package com.br.clean.arch.application.usecases.address.delivery;

import java.util.List;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;

public class ListDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public ListDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public List<Delivery> createDelivery() {
		return this.repositoryDelivery.listDelivery();
	}
}
