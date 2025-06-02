package com.br.clean.arch.application.usecases.address.delivery.dto.input;

import jakarta.validation.constraints.NotNull;

public record UpdateDeliveryCommand(
		
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
		String deliveryPhrase,
		String state, 
		String country
		
		) {
}
