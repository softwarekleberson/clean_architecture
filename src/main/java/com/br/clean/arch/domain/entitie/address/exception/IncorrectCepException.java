package com.br.clean.arch.domain.entitie.address.exception;

public class IncorrectCepException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IncorrectCepException(String message) {
		super(message);
	}
}
