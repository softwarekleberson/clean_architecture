package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;

public class UpdateCharge {

	private RepositoryCharge repository;
	
	public UpdateCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge updateCharge(Long id, ChargeUpdateDto dto) {
		return repository.updateCharge(id, dto);
	}
}
