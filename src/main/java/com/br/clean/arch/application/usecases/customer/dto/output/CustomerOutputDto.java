package com.br.clean.arch.application.usecases.customer.dto.output;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;

public record CustomerOutputDto(
		
		String id,
		String cpf,
		String name,
		Email email,
		boolean active
		
		) {

}
