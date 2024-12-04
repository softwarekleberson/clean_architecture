package com.br.clean.arch.infra.controller.customer;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CustomerInsertDto(
	
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
	
	@NotBlank(message = "Confirm password need be igual password")
	String confirmPassword,
	
	@NotNull
	Gender gender,
	
	@NotBlank(message = "Report ddd and phone")
	Phone ddd,
	
	@NotNull
	Email email
		
								) {
}
