package com.br.clean.arch.infra.gateways.card;

import java.util.List;
import java.util.Optional;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.infra.controller.card.CardUpdateDto;
import com.br.clean.arch.infra.persistence.card.CardEntity;
import com.br.clean.arch.infra.persistence.card.CardRepositoy;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class CardRepositoryJpa implements RepositoryCard{

	private final CustomerRepository customerRepository;
	private final CardRepositoy cardRepositoy;
	private final CardEntityMapper mapper;
	
	public CardRepositoryJpa(CustomerRepository customerRepository, CardRepositoy cardRepositoy, CardEntityMapper mapper) {
		this.cardRepositoy = cardRepositoy;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	@Override
	public List<Card> listCard(String id) {
		return null;
	}

	@Override
	public Card createNewCard(String cpf, Card card) {
		CustomerEntity customer = customerRepository.findByCpf(cpf);
		if(customer == null) {
			throw new IllegalArgumentException("Customer not found");
		}
		
		CardEntity entity = mapper.toEntity(card);
		entity.setCustomerEntity(customer);
		
		cardRepositoy.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public Card updateCard(String id, CardUpdateDto dto) {
		return null;
	}
	
}
