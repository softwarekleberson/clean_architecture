package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.charge.dto.input.CreateChargeCommand;
import com.br.clean.arch.application.usecases.address.charge.dto.output.ChargeOutputDto; // Importe seu DTO de saída
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class CreateCharge {

	private RepositoryCharge repository;
	private RepositoryCustomer repositoryCustomer;

	public CreateCharge(RepositoryCharge repository, RepositoryCustomer repositoryCustomer) {
		this.repository = repository;
		this.repositoryCustomer = repositoryCustomer;
	}

	public ChargeOutputDto createCharge(String cpf, CreateChargeCommand dto) {

		Customer customer = repositoryCustomer.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		Charge charge = new Charge(dto.main(), dto.receiver(), dto.street(),
								   dto.number(), dto.neighborhood(), dto.cep(),
								   dto.observation(), dto.street(), dto.typeResidence(), dto.city(),
								   dto.state(), dto.country());

		customer.addNewCharge(charge);
		Charge savedCharge = this.repository.save(cpf, charge); 

		return new ChargeOutputDto(
		    savedCharge.getId(),
		    savedCharge.getReceiver(),
		    savedCharge.getStreet(),
		    savedCharge.getNumber(),
		    savedCharge.getNeighborhood(),
		    savedCharge.getCep()
		);
	}
}