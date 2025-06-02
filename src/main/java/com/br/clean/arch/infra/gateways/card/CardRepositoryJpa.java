package com.br.clean.arch.infra.gateways.card;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;
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

    public CardRepositoryJpa(CustomerRepository customerRepository, 
                             CardRepositoy repository, 
                             CardEntityMapper mapper, 
                             CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.repository = repository;
        this.mapper = mapper;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public Page<Card> listCard(String customerId, Pageable pageable) {
        return repository.findByCustomerId(customerId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Card createNewCard(String id, Card card) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CardEntity entity = mapper.toEntity(card);
        entity.setCustomerEntity(customerEntity);

        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public void deleteCard(Long id) {
        CardEntity cardEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        repository.delete(cardEntity);
    }

    @Override
    public boolean registeredCard(String numberCard) {
        return repository.existsByNumberCard(numberCard);
    }

    @Override
    public Optional<Customer> fidByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
    }

	@Override
	public Optional<Card> findById(Long id) {
		return repository.findById(id).map(mapper::toDomain);
	}
}
