package com.br.clean.arch.domain.entitie.customer.exceptions;

public class DuplicateEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
		super(message);
	}
}
