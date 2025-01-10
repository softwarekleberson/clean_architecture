package com.br.clean.arch.infra.gateways.card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Card> listCard(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Card createNewCard(String cpf, Card card) {
        CustomerEntity customerEntity = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CardEntity entity = mapper.toEntity(card);
        entity.setCustomerEntity(customerEntity);

        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Card deleteCard(Long id) {
        CardEntity cardEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        repository.delete(cardEntity);
        return mapper.toDomain(cardEntity);
    }

    @Override
    public boolean registeredCard(String numberCard) {
        return repository.existsByNumberCard(numberCard);
    }

    @Override
    public Optional<Customer> getCustomerByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
    }
}
