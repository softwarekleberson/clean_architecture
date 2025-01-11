package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.address.exception.IncorrectAddressException;

public class DeleteCharge {

	private RepositoryCharge repository;
	
	public DeleteCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge deleteCharge(Long id) {
		repository.findById(id).orElseThrow(() -> new IncorrectAddressException("Delivery not found with id : " + id));
		return repository.deleteCharge(id);
	}
}
