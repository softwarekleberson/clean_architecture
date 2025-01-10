package com.br.clean.arch.infra.controller.charge;

public record ChargeListDto(
		
		Long id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep
		
		) {
}
