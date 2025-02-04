package com.br.clean.arch.infra.controller.customer.input;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CustomerDto(
	
    @NotBlank(message = "CPF not be empity our null")
	String cpf,

	@NotBlank(message = "Name is required")
	String name,
	
	@NotNull(message = "Birth is required")
    @Past(message = "Date of birth must be a past date")
	LocalDate birth,
	
	@NotBlank(message = "Password must contain at least 8 characters:"
						+ " uppercase, lowercase, number and"
						+ " special character")
	String password,
	
	@NotBlank
	String confirmPassword,
	
	@NotNull
	Gender gender,
	
	@NotNull(message = "Report ddd and phone")
	Phone phone,
	
	@NotNull
	Email email
		
								) {
}
