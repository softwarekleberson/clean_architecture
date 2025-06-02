package com.br.clean.arch.application.usecases.address.charge.dto.input;

import jakarta.validation.constraints.NotNull;

public record UpdateChargeCommand(
		
		@NotNull
		Long id,
		Boolean main,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep,
		String observation,
		String streetType,
		String typeResidence,
		String city,
		String state, 
		String country
		
		) {
}
