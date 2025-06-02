package com.br.clean.arch.application.gateways.card;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;

public interface RepositoryCard {

	Page<Card> listCard (String id, Pageable pageable);
	Card createNewCard (String id, Card card);
	void deleteCard (Long id);
	boolean registeredCard(String numberCard);
	Optional<Customer> fidByCpf(String cpf);
	Optional<Card> findById(Long id);
}
