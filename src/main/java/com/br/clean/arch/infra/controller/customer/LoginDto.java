package com.br.clean.arch.infra.controller.customer;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto (
	
	   @NotBlank
	   String password,
		
	   @NotNull
	   Email email
						) 

						{

}
