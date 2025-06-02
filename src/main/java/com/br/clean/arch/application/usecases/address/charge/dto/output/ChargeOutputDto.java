package com.br.clean.arch.application.usecases.address.charge.dto.output;

public record ChargeOutputDto(
		
		Long id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep
		
		) {
}
