package com.br.clean.arch.application.gateways.card;

import java.util.List;
import java.util.Optional;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;

public interface RepositoryCard {

	List<Card> listCard (String id);
	Card createNewCard (String id, Card card);
	Card deleteCard (Long id);
	boolean registeredCard(String numberCard);
	Optional<Customer> getCustomerByCpf(String cpf);
}
