package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;

public interface RepositoryCharge {
	
	List<Charge> listCharge (String customerId);
	Charge createCharge(String cpf, Charge charge);
	Charge updateCharge(Long id, ChargeUpdateDto dto);
	Charge deleteCharge(Long id);
	Charge ensuresAprimaryAddress(String cpf, boolean main);
	Charge customerIsActive(String id);
}
