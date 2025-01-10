package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class CreateCharge {

	private RepositoryCharge repository;
	
	public CreateCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge createCharge(String cpf, Charge charge) {
		if(repository.findByCpf(cpf).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return this.repository.createCharge(cpf, charge);
	}
}
