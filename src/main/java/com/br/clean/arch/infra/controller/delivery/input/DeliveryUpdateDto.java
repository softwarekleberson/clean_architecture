package com.br.clean.arch.infra.controller.delivery.input;

import jakarta.validation.constraints.NotNull;

public record DeliveryUpdateDto(
		
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
		String deliveryPhrase
		
		) {
}
