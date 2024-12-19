package com.br.clean.arch.infra.controller.delivery;

public record DeliveryUpdateDto(
		
		String receiver,
		String street,
		String number,
		String neighborhood,
		String cep,
		String observation,
		String streetType,
		String typeResidence,
		String city,
		String deliveryPhrase
		
		) {
}
