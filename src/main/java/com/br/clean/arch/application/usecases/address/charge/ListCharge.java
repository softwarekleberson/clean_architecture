package com.br.clean.arch.application.usecases.address.charge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.charge.dto.output.ChargeOutputDto;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class ListCharge {

	private RepositoryCharge repository;
	private RepositoryCustomer repositoriyCustomer;
	
	public ListCharge(RepositoryCharge repository, RepositoryCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Page<ChargeOutputDto> listCharge(String customerId, Pageable pageable) {
		if(repositoriyCustomer.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		
		Page<Charge> charges = repository.listCharge(customerId, pageable);
		return charges.map(charge -> new ChargeOutputDto(
											  charge.getId(),
											  charge.getReceiver(),
											  charge.getStreet(),
											  charge.getNumber(),
											  charge.getNeighborhood(),
											  charge.getCep()));
	}
}