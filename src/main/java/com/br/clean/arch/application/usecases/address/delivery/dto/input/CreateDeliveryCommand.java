package com.br.clean.arch.application.usecases.address.delivery.dto.input;

import jakarta.validation.constraints.NotBlank;

public record CreateDeliveryCommand(
		
		boolean main,
		
		@NotBlank
		String receiver,
		
		@NotBlank
		String street,
		
		@NotBlank
		String number,
		
		@NotBlank
		String neighborhood,
		
		@NotBlank
		String cep,
		
		String observation,
		
		@NotBlank
		String streetType,
		
		@NotBlank
		String typeResidence,
		
		@NotBlank
		String city,
		
		@NotBlank
		String state,
		
		@NotBlank
		String country,
		
		@NotBlank
		String deliveryPhrase
		
		) {
}
