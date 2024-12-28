package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class DeleteCharge {

	private RepositoryCharge repositoryCharge;
	
	public DeleteCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public Charge deleteCharge(Long id) {
		return repositoryCharge.deleteCharge(id);
	}
}
