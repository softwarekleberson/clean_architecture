package com.br.clean.arch.domain.entitie.card.exeptions;

public class IncorrectCardExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectCardExpetion(String message) {
		super(message);
	}
}
