package com.br.clean.arch.application.usecases.address.charge;

import java.util.List;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;

public class ListCharge {

	private RepositoryCharge repositoryCharge;
	
	public ListCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public List<Charge> listCharge(String custonId) {
		return this.repositoryCharge.listCharge(custonId);
	}
}
