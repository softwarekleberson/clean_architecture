package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.application.usecases.card.CreateCard;
import com.br.clean.arch.infra.gateways.card.CardEntityMapper;
import com.br.clean.arch.infra.gateways.card.CardRepositoryJpa;
import com.br.clean.arch.infra.persistence.card.CardRepositoy;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class CardConfig {

	@Bean
	public CreateCard createCard(RepositoryCard repositoriyCard) {
		return new CreateCard(repositoriyCard);
	}
	
	@Bean
    public CardRepositoryJpa cardRepositoryJpa(CustomerRepository customerRepository, CardRepositoy repository, CardEntityMapper mapper) {
        return new CardRepositoryJpa(customerRepository, repository, mapper);
    }
	
	@Bean
	public CardEntityMapper cardEntityMapper() {
		return new CardEntityMapper();
	}
}
