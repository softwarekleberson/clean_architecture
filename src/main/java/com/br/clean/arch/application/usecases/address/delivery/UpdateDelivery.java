package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.controller.delivery.DeliveryUpdateDto;

public class UpdateDelivery {

	private RepositoryDelivery repository;
	
	public UpdateDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery updateDelivery(Long id, DeliveryUpdateDto dto){ 
		return repository.updateDelivery(id, dto);
	}
}
