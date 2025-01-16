package com.br.clean.arch.domain.entitie.customer.exceptions;

public class IllegalCpfException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalCpfException(String message) {
		super(message);
	}
}
