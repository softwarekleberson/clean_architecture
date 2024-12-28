package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class CustomerIsActive {

	private RepositoryCharge repositoryCharge;

	public CustomerIsActive(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public Charge customerIsActive(String id) {
		return this.repositoryCharge.customerIsActive(id);
	}
}
