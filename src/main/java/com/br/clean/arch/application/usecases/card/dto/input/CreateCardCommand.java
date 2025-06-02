package com.br.clean.arch.application.usecases.card.dto.input;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.card.valueObject.Flag;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCardCommand(
		
		@NotNull
		boolean main,
		
		@NotBlank
		String printedName,
		
		@NotBlank
		String code,
		
		@NotBlank
		String numberCard,
		
		@NotNull
		Flag flag,
		
		@NotNull
		@Future(message = "Expiration date needs to be in the future")
		LocalDate expirationDate
		
		) {
}
