package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.domain.entitie.address.Delivery;

public interface RepositoryDelivery {

	List<Delivery> listDelivery ();
	Delivery createDelivery(String cpf, Delivery delivery);
}
