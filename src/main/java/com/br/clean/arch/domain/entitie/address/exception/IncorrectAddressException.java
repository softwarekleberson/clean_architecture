package com.br.clean.arch.domain.entitie.address.exception;

public class IncorrectAddressException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectAddressException(String message) {
		super(message);
	}
}
