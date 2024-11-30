package com.br.clean.arch.application.usecases.address.charge;

import java.util.List;

import com.br.clean.arch.address.domain.entitie.address.Charge;
import com.br.clean.arch.application.gateways.address.RepositoryCharge;

public class ListCharge {

	private RepositoryCharge repositoryCharge;
	
	public ListCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public List<Charge> createCharge() {
		return this.repositoryCharge.listCharge();
	}
}
