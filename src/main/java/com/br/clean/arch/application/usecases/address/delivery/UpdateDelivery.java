package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.usecases.address.delivery.dto.input.UpdateDeliveryCommand;
import com.br.clean.arch.application.usecases.address.delivery.dto.output.DeliveryOutputDto;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.address.exception.AddressNotFoundException;

public class UpdateDelivery {

	private RepositoryDelivery repository;
	
	public UpdateDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public DeliveryOutputDto updateDelivery(Long id, UpdateDeliveryCommand dto){ 
		Delivery delivery = repository.findById(id).orElseThrow(() -> new AddressNotFoundException("Delivery not found with id : " + id));		
		
		delivery.updateDetails(
				dto.main(),
				dto.receiver(),
				dto.street(),
				dto.number(),
				dto.neighborhood(),
				dto.cep(),
				dto.observation(),
				dto.streetType(),
				dto.typeResidence(),
				dto.city(),
				dto.state(),
				dto.country()
		);
		
		Delivery savedDelivery = repository.updateDelivery(id, delivery);
		return new DeliveryOutputDto 
				(savedDelivery.getId(),
				savedDelivery.getReceiver(),
				savedDelivery.getStreet(),
				savedDelivery.getNumber(),
				savedDelivery.getNeighborhood(),
				savedDelivery.getCep(),
				savedDelivery.getObservation()
				);
	}
}
