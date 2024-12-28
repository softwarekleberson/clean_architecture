package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;

public class UpdateCharge {

	private RepositoryCharge repositoryCharge;
	
	public UpdateCharge(RepositoryCharge repositoryCharge) {
		this.repositoryCharge = repositoryCharge;
	}
	
	public Charge updateCharge(Long id, ChargeUpdateDto dto) {
		return repositoryCharge.updateCharge(id, dto);
	}
}
