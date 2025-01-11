package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.address.exception.IncorrectAddressException;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;

public class UpdateCharge {

	private RepositoryCharge repository;
	
	public UpdateCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public Charge updateCharge(Long id, ChargeUpdateDto dto) {
		repository.findById(id).orElseThrow(() -> new IncorrectAddressException("Delivery not found with id : " + id));
		return repository.updateCharge(id, dto);
	}
}
