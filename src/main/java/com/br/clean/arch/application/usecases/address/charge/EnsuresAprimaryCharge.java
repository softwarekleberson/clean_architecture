package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class EnsuresAprimaryCharge {

	private RepositoryCharge repository;
	
	public EnsuresAprimaryCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge ensuresAprimaryCharge(String cpf, boolean main) {
		return this.repository.ensuresAprimaryAddress(cpf, main);
	}
}
