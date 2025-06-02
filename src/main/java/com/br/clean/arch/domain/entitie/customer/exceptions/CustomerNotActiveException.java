package com.br.clean.arch.domain.entitie.customer.exceptions;

public class CustomerNotActiveException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerNotActiveException(String message) {
		super(message);
	}
}
