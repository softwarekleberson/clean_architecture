package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.card.exeptions.DuplicateCardException;

public class CreateCard {

	private RepositoryCard repositoy;
	
	public CreateCard(RepositoryCard repositoy) {
		this.repositoy = repositoy;
	}
	
	public Card createCard(String id, Card card) {
		
		customerPresentCard(id);
		registeredCard(card.getNumberCard());
				
		return this.repositoy.createNewCard(id, card);
	}
	
	private void customerPresentCard(String id) {
		if(repositoy.getCustomerById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
	}
	
	private void registeredCard(String numberCard) {
		if(repositoy.registeredCard(numberCard)) {
			throw new DuplicateCardException("This card at registered");
		}
	}
}
