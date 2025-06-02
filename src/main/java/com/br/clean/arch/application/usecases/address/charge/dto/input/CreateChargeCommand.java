package com.br.clean.arch.application.usecases.address.charge.dto.input;

import jakarta.validation.constraints.NotBlank;

public record CreateChargeCommand(
		
		Boolean main,
		
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
		String country
		
		) {
}
