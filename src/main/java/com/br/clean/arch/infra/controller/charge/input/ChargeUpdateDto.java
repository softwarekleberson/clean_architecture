package com.br.clean.arch.infra.controller.charge.input;

import jakarta.validation.constraints.NotNull;

public record ChargeUpdateDto(
		
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
		String city
		
		) {
}
