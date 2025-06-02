package com.br.clean.arch.domain.entitie.card.exeptions;

public class InvalidCardDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCardDataException(String message) {
		super(message);
	}
}
