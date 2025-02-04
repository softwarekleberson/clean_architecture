package com.br.clean.arch.infra.controller.charge.output;

public record ChargeListDto(
		
		Long id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep
		
		) {
}
