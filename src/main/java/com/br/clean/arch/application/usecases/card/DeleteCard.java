package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;

public class DeleteCard {

	private RepositoryCard repository;
	
	public DeleteCard(RepositoryCard repository) {
		this.repository = repository;
	}
	
	public Card deleteCard(Long id) {
		return repository.deleteCard(id);
	}
}
