package com.br.clean.arch.application.usecases.card;

import java.util.List;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class ListCard {

	private RepositoryCard repository;
	
	public ListCard(RepositoryCard repository) {
		this.repository = repository;
	}
	
	public List<Card> listCards(String id){
		if(repository.getCustomerById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return repository.listCard(id);
	}
}
