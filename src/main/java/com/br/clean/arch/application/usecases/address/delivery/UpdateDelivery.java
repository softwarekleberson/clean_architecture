package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.address.exception.IncorrectAddressException;
import com.br.clean.arch.infra.controller.delivery.input.DeliveryUpdateDto;

public class UpdateDelivery {

	private RepositoryDelivery repository;
	
	public UpdateDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public Delivery updateDelivery(Long id, DeliveryUpdateDto dto){ 
		repository.findById(id).orElseThrow(() -> new IncorrectAddressException("Delivery not found with id : " + id));		
		return repository.updateDelivery(id, dto);
	}
}
