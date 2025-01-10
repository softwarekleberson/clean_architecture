package com.br.clean.arch.application.gateways.address;

import java.util.List;
import java.util.Optional;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;

public interface RepositoryCharge {
	
	List<Charge> listCharge (String customerId);
	Charge createCharge(String cpf, Charge charge);
	Charge updateCharge(Long id, ChargeUpdateDto dto);
	Charge deleteCharge(Long id);
	Charge ensuresAprimaryAddress(String cpf, boolean main);
	Charge customerIsActive(String id);
	Optional<Customer> findByCpf(String cpf);
}
