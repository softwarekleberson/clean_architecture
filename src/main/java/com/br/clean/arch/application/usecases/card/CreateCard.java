package com.br.clean.arch.application.usecases.card;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.card.dto.input.CreateCardCommand;
import com.br.clean.arch.application.usecases.card.dto.output.CardOutputDto;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class CreateCard {

	private RepositoryCard repositoy;
	private RepositoryCustomer repositoryCustomer;
	
	public CreateCard(RepositoryCard repositoy, RepositoryCustomer repositoryCustomer) {
		this.repositoy = repositoy;
		this.repositoryCustomer = repositoryCustomer;
	}
	
	public CardOutputDto createCard(String id, CreateCardCommand dto) {		
		Customer customer = repositoryCustomer.findById(id)
		        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
				
		Card card = new Card(dto.main(), dto.printedName(),
							 dto.code(), dto.numberCard(),
							 dto.flag(), dto.expirationDate());
		
		customer.addNewCard(card);
		
		Card savedCard = repositoy.createNewCard(id, card);
		return new CardOutputDto(savedCard.getId(),
								 savedCard.isMain(),
								 savedCard.getPrintedName(), 
								 savedCard.getCode(), 
								 savedCard.getNumberCard());
	}
}
