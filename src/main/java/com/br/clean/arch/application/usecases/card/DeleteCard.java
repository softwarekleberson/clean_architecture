package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.exeptions.CardNotFoundException;

public class DeleteCard {

	private RepositoryCard repository;
	
	public DeleteCard(RepositoryCard repository) {
		this.repository = repository;
	}
	
	public void deleteCard(Long id) {
		if(repository.findById(id).isEmpty()) {
			throw new CardNotFoundException("Card not found with id : " + id);
		}
		repository.deleteCard(id);
	}
}
