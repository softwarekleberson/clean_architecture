package com.br.clean.arch.application.usecases.card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.card.dto.output.CardOutputDto;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class ListCard {

	private RepositoryCard repository;
	private RepositoryCustomer repositoriyCustomer;
	
	public ListCard(RepositoryCard repository, RepositoryCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public Page<CardOutputDto> listCards(String id, Pageable pageable){
		if(repositoriyCustomer.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		
		Page<Card> cards = repository.listCard(id, pageable);
		return cards.map(card -> new CardOutputDto(card.getId(),
									 card.isMain(),
									 card.getPrintedName(),
									 card.getCode(),
									 card.getNumberCard()
									 ));
	}
}