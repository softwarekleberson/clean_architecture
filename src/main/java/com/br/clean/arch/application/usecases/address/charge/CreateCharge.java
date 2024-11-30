package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.address.domain.entitie.address.Charge;
import com.br.clean.arch.application.gateways.address.RepositoryCharge;

public class CreateCharge {

	private RepositoryCharge repositoryCharge;
	
	public CreateCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public Charge createCharge(Charge charge) {
		return this.repositoryCharge.createCharge(charge);
	}
}
