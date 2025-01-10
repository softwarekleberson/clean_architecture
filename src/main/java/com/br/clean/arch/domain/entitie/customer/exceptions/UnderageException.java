package com.br.clean.arch.domain.entitie.customer.exceptions;

public class UnderageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnderageException(String message) {
		super(message);
	}
}
