package com.br.clean.arch.application.usecases.card.dto.output;

public record CardOutputDto(
		
		Long id,
		boolean main,
		String printedName,
		String code,
		String numberCard
		
		) {
}
