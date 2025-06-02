package com.br.clean.arch.application.gateways.address;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;

public interface RepositoryDelivery {

	Page<Delivery> listDelivery (String customerId, Pageable pageable);
	Delivery createDelivery(String cpf, Delivery delivery);
	Delivery updateDelivery(Long id, Delivery delivery);
	void deleteDelivery(Long id);
	Delivery ensuresAprimaryAddress(String cpf, boolean main);
	Delivery customerIsActive(String id);
	Optional<Customer> findByCpf(String cpf);
	Optional<Delivery> findById(Long id);
}
