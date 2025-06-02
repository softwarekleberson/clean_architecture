package com.br.clean.arch.application.usecases.customer.dto.input;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

public record UpdateCustomerCommand(
		
		String name,
		LocalDate birth,
		Phone phone
		
		) {

}
