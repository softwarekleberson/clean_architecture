package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class EnsuresAprimaryCharge {

	private RepositoryCharge repositoryCharge;
	
	public EnsuresAprimaryCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public Charge ensuresAprimaryCharge(String cpf, boolean main) {
		return this.repositoryCharge.ensuresAprimaryAddress(cpf, main);
	}
}
