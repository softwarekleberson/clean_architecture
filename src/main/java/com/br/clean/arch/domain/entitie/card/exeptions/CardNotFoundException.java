package com.br.clean.arch.domain.entitie.card.exeptions;

public class CardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CardNotFoundException(String message) {
		super(message);
	}
}
