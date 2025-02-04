package com.br.clean.arch.infra.controller.charge.input;

public record ChargeUpdateDto(
		
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
