package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;

public class CreateCard {

	private RepositoryCard repositoyCard;
	
	public CreateCard(RepositoryCard repositoyCard) {
		this.repositoyCard = repositoyCard;
	}
	
	public Card createCard(String id, Card card) {
		return this.repositoyCard.createNewCard(id, card);
	}
}
