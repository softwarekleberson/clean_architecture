package com.br.clean.arch.application.usecases.address.delivery.dto.output;

public record DeliveryOutputDto(
		
		Long id,
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep,
		String observation
		
		) {
}
