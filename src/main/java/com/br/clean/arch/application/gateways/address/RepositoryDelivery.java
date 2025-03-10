package com.br.clean.arch.application.gateways.address;

import java.util.List;
import java.util.Optional;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.delivery.input.DeliveryUpdateDto;

public interface RepositoryDelivery {

	List<Delivery> listDelivery (String customerId);
	Delivery createDelivery(String cpf, Delivery delivery);
	Delivery updateDelivery(Long id, DeliveryUpdateDto dto);
	Delivery deleteDelivery(Long id);
	Delivery ensuresAprimaryAddress(String cpf, boolean main);
	Delivery customerIsActive(String id);
	Optional<Customer> findByCpf(String cpf);
	Optional<Delivery> findById(Long id);
}
