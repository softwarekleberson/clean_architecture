package com.br.clean.arch.infra.controller.delivery;

public record DeliveryListDto(
		
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep,
		String observation
		
		) {
}
