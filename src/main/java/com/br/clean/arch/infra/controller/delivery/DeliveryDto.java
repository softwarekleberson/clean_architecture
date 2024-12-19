package com.br.clean.arch.infra.controller.delivery;

import jakarta.validation.constraints.NotBlank;

public record DeliveryDto(
		
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
		
		@NotBlank
		String observation,
		
		@NotBlank
		String streetType,
		
		@NotBlank
		String typeResidence,
		
		@NotBlank
		String city,
		
		@NotBlank
		String deliveryPhrase
		
		) {
}
