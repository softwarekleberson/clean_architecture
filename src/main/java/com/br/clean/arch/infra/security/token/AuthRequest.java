package com.br.clean.arch.infra.security.token;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequest (
	
	   @NotBlank
	   String password,
		
	   @NotNull
	   Email email
						) 

						{

}
