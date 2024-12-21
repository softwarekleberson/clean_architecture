package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;

public class DeleteCard {

	private RepositoryCard repositoryCard;
	
	public DeleteCard(RepositoryCard repositoryCard) {
		this.repositoryCard = repositoryCard;
	}
	
	public Card deleteCard(Long id) {
		return repositoryCard.deleteCard(id);
	}
}
