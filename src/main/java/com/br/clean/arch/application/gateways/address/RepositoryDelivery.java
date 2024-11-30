package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.address.domain.entitie.address.Delivery;

public interface RepositoryDelivery {

	Delivery createDelivery (Delivery delivery);
	List<Delivery> listDelivery ();
}
