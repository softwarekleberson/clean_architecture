package com.br.clean.arch.domain.entitie.customer.exceptions;

public class IncorretEmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IncorretEmailException(String message) {
		super(message);
	}
}
