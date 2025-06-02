package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.delivery.dto.input.CreateDeliveryCommand;
import com.br.clean.arch.application.usecases.address.delivery.dto.output.DeliveryOutputDto;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class CreateDelivery {

	private RepositoryDelivery repository;
	private RepositoryCustomer repositoryCustomer;
	
	public CreateDelivery(RepositoryDelivery repository, RepositoryCustomer repositoryCustomer) {
		this.repository = repository;
		this.repositoryCustomer = repositoryCustomer;
	}
	
	public DeliveryOutputDto createDelivery(String cpf, CreateDeliveryCommand dto) {
		Customer customer = repositoryCustomer.findByCpf(cpf)
		        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
				
			Delivery delivery = new Delivery(
					 
				dto.main(), dto.receiver(), dto.street(),
				dto.number(), dto.neighborhood(), dto.cep(),
				dto.observation() != null ? dto.observation() : "",
				dto.streetType(), dto.typeResidence(),
				dto.city(), dto.state(), dto.country(), dto.deliveryPhrase()
					
			);
			
			customer.addNewDelivery(delivery);
			
			Delivery savedDelivy = this.repository.createDelivery(cpf, delivery);
			return new DeliveryOutputDto (
					savedDelivy.getId(),
					savedDelivy.getReceiver(),
					savedDelivy.getStreet(),
					savedDelivy.getNumber(),
					savedDelivy.getNeighborhood(),
					savedDelivy.getCep(),
					savedDelivy.getObservation()
					);
	}
}
