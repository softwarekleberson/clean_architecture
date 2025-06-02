package com.br.clean.arch.application.usecases.address.delivery;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.exception.AddressNotFoundException;

public class DeleteDelivery {

	private RepositoryDelivery repository;
	
	public DeleteDelivery(RepositoryDelivery repository) {
		this.repository = repository;
	}
	
	public void deleteDelivery(Long id) {
		repository.findById(id).orElseThrow(() -> new AddressNotFoundException("Delivery not found with id : " + id));		
		repository.deleteDelivery(id);
	} 
}
