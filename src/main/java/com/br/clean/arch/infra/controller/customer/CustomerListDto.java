package com.br.clean.arch.infra.controller.customer;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;

public record CustomerListDto(
		
		String cpf,
		String name,
		Email email
		
		) {

}
