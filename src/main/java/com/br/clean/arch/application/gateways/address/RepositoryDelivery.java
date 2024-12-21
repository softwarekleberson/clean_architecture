package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.controller.delivery.DeliveryUpdateDto;

public interface RepositoryDelivery {

	List<Delivery> listDelivery (String customerId);
	Delivery createDelivery(String cpf, Delivery delivery);
	Delivery updateDelivery(Long id, DeliveryUpdateDto dto);
	Delivery deleteDelivery(Long id);
	Delivery verifyMainDelivery(String cpf);
	Delivery ensuresAprimaryAddress(String cpf, boolean principal);
}
