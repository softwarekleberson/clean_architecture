package com.br.clean.arch.application.usecases.address.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.delivery.dto.output.DeliveryOutputDto;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class ListDelivery {

	private RepositoryDelivery repository;
	private RepositoryCustomer repositoriyCustomer;
	
	public ListDelivery(RepositoryDelivery repository, RepositoryCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Page<DeliveryOutputDto> listDelivery(String customerId, Pageable pageable) {
		if(repositoriyCustomer.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		
		Page<Delivery> deliverys = repository.listDelivery(customerId, pageable);
		return deliverys.map(delivery -> new DeliveryOutputDto(delivery.getId(),
											delivery.getReceiver(),
											delivery.getStreet(),
											delivery.getNumber(), 
											delivery.getNeighborhood(), 
											delivery.getCep(),
											delivery.getObservation()));
	}
}