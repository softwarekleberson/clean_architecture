package com.br.clean.arch.application.usecases.card;

import java.util.List;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;

public class ListCard {

	private RepositoryCard repository;
	private RepositoryCustomer repositoriyCustomer;
	
	public ListCard(RepositoryCard repository, RepositoryCustomer repositoriyCustomer) {
		this.repository = repository;
		this.repositoriyCustomer = repositoriyCustomer;
	}
	
	public List<Card> listCards(String id){
		if(repositoriyCustomer.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return repository.listCard(id);
	}

}
