package com.br.clean.arch.application.usecases.card;

import java.util.List;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;

public class ListCard {

	private RepositoryCard repositoryCard;
	
	public ListCard(RepositoryCard repositoryCard) {
		this.repositoryCard = repositoryCard;
	}
	
	public List<Card> listCards(String id){
		return repositoryCard.listCard(id);
	}
}
