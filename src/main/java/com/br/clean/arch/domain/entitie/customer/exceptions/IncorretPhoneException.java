package com.br.clean.arch.domain.entitie.customer.exceptions;

public class IncorretPhoneException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IncorretPhoneException(String message) {
		super(message);
	}
}
