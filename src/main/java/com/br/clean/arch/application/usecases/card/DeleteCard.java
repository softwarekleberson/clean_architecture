package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.exeptions.IncorrectCardExpetion;

public class DeleteCard {

	private RepositoryCard repository;
	
	public DeleteCard(RepositoryCard repository) {
		this.repository = repository;
	}
	
	public Card deleteCard(Long id) {
		if(repository.findById(id).isEmpty()) {
			throw new IncorrectCardExpetion("Card not found with id : " + id);
		}
		return repository.deleteCard(id);
	}
}
