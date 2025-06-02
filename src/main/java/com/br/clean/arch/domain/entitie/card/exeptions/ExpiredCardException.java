package com.br.clean.arch.domain.entitie.card.exeptions;

public class ExpiredCardException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExpiredCardException(String message) {
		super(message);
	}
}
