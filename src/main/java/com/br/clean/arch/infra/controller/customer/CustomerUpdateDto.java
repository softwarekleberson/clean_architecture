package com.br.clean.arch.infra.controller.customer;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

public record CustomerUpdateDto(
		
		String name,
		LocalDate birth,
		Phone phone
		
		) {

}
