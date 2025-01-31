package com.br.clean.arch.infra.controller.delivery;

public record DeliveryListDto(
		
		Long id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep,
		String observation
		
		) {
}
