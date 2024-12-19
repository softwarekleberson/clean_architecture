package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.controller.delivery.DeliveryUpdateDto;

public class UpdateDelivery {

	private RepositoryDelivery repositoryDelivery;
	
	public UpdateDelivery(RepositoryDelivery repositoryDelivery) {
		this.repositoryDelivery = repositoryDelivery;
	}
	
	public Delivery updateDelivery(Long id, DeliveryUpdateDto dto){ 
		return repositoryDelivery.updateDelivery(id, dto);
	}
}
