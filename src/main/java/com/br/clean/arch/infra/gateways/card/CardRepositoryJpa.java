package com.br.clean.arch.infra.gateways.card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.card.CardUpdateDto;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.card.CardEntity;
import com.br.clean.arch.infra.persistence.card.CardRepositoy;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class CardRepositoryJpa implements RepositoryCard {

	private final CustomerRepository customerRepository;
	private final CardRepositoy repository;
	private final CardEntityMapper mapper;
	private final CustomerEntityMapper customerEntityMapper;
	
	public CardRepositoryJpa(CustomerRepository customerRepository, CardRepositoy repository, CardEntityMapper mapper, CustomerEntityMapper customerEntityMapper) {
		this.repository = repository;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
		this.customerEntityMapper = customerEntityMapper;
	}

	@Override
	public List<Card> listCard(String id) {
		return repository.findByCustomerId(id).stream()
				   .map(mapper::toDomain)
				   .collect(Collectors.toList());
	}

	@Override
	public Card createNewCard(String cpf, Card card) {
		Optional<CustomerEntity> optDataBase = customerRepository.findByCpf(cpf);
		if(optDataBase.isEmpty()) {
			throw new IllegalArgumentException("Customer not found");
		}
		
		CustomerEntity customerEntity = optDataBase.get();
		CardEntity entity = mapper.toEntity(card);
		entity.setCustomerEntity(customerEntity);
		
		repository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public Card updateCard(String id, CardUpdateDto dto) {
		return null;
	}

	@Override
	public Card deleteCard(Long id) {
		Optional<CardEntity> optDataBase = repository.findById(id);
		if(optDataBase.isPresent()) {
			CardEntity entity = optDataBase.get();
			repository.delete(entity);
		}
		return null;
	}

	@Override
	public boolean registeredCard(String numberCard) {
		 return repository.existsByNumberCard(numberCard);
	}

	@Override
	public Optional<Customer> getCustomerById(String cpf) {
        return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
	}
}
