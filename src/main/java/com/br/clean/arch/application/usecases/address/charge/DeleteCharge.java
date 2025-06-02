package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.exception.AddressNotFoundException;

public class DeleteCharge {

	private RepositoryCharge repository;
	
	public DeleteCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public void deleteCharge(Long id) {
		repository.findById(id).orElseThrow(() -> new AddressNotFoundException("Delivery not found with id : " + id));
		repository.deleteCharge(id);
	}
}
