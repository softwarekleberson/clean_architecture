package com.br.clean.arch.infra.controller.customer.output;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;

public record CustomerListDto(
		
		String id,
		String cpf,
		String name,
		Email email,
		Boolean active
		
		) {

}
