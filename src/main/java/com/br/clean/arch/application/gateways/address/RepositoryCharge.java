package com.br.clean.arch.application.gateways.address;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;

public interface RepositoryCharge {
	
	Page<Charge> listCharge (String customerId, Pageable pageable);
	Charge save(String cpf, Charge charge);
	Charge updateCharge(Long id, Charge charge);
	void deleteCharge(Long id);
	Charge ensuresAprimaryAddress(String cpf, boolean main);
	Optional<Customer> findByCpf(String cpf);
	Optional<Charge> findById(Long id);
}
