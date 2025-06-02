package com.br.clean.arch.application.usecases.address.charge;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.usecases.address.charge.dto.input.UpdateChargeCommand;
import com.br.clean.arch.application.usecases.address.charge.dto.output.ChargeOutputDto;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.address.exception.AddressNotFoundException;

public class UpdateCharge {

	private RepositoryCharge repository;
	
	public UpdateCharge(RepositoryCharge repository) {
		this.repository = repository;
	}
	
	public ChargeOutputDto updateCharge(Long id, UpdateChargeCommand dto) {
		Charge charge = repository.findById(id)
		.orElseThrow(() -> new AddressNotFoundException
		("Delivery not found with id : " + id));
		
		charge.updateDetails(
				dto.main(),
				dto.receiver(),
				dto.street(),
				dto.number(),
				dto.neighborhood(),
				dto.cep(),
				dto.observation(),
				dto.streetType(),
				dto.typeResidence(),
				dto.city(),
				dto.state(),
				dto.country()
		);
		
		Charge savedCharge = repository.updateCharge(id, charge);
		return new ChargeOutputDto (
				savedCharge.getId(),
				savedCharge.getReceiver(),
				savedCharge.getStreet(),
				savedCharge.getNumber(),
				savedCharge.getNeighborhood(),
				savedCharge.getCep()
				);
	}
}
