package com.br.clean.arch.infra.controller.card;

import java.time.LocalDate;

public record CardUpdateDto(
		
		boolean main,
		String printedName,
		LocalDate expirationDate
		
		) {
}
