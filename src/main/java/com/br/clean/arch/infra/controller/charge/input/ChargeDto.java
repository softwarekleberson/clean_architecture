package com.br.clean.arch.infra.controller.charge.input;

import jakarta.validation.constraints.NotBlank;

public record ChargeDto(
		
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
		String city
		
		) {
}
