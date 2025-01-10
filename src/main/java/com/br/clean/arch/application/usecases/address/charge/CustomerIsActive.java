package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class CustomerIsActive {

	private RepositoryCharge repository;

	public CustomerIsActive(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge customerIsActive(String id) {
		return this.repository.customerIsActive(id);
	}
}
