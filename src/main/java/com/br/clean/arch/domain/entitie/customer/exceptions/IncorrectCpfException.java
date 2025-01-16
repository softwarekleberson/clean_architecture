package com.br.clean.arch.domain.entitie.customer.exceptions;

public class IncorrectCpfException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectCpfException(String message) {
		super(message);
	}
}
