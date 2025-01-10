package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.card.RepositoryCard;
import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.application.usecases.card.CreateCard;
import com.br.clean.arch.application.usecases.card.DeleteCard;
import com.br.clean.arch.application.usecases.card.ListCard;
import com.br.clean.arch.infra.gateways.card.CardEntityMapper;
import com.br.clean.arch.infra.gateways.card.CardRepositoryJpa;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.card.CardRepositoy;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class CardConfig {

	@Bean
	public CreateCard createCard(RepositoryCard repositoriyCard) {
		return new CreateCard(repositoriyCard);
	}
	
	@Bean
	public ListCard listCard(RepositoryCard repositoriyCard, RepositoriyCustomer repositoriyCustomer) {
		return new ListCard(repositoriyCard, repositoriyCustomer);
	}
	
	@Bean
	public DeleteCard deleteCard(RepositoryCard repositoriyCard) {
		return new DeleteCard(repositoriyCard);
	}
	
	@Bean
    public CardRepositoryJpa cardRepositoryJpa(CustomerRepository customerRepository, CardRepositoy repository, CardEntityMapper mapper, CustomerEntityMapper customerEntityMapper) {
        return new CardRepositoryJpa(customerRepository, repository, mapper, customerEntityMapper);
    }
	
	@Bean
	public CardEntityMapper cardEntityMapper() {
		return new CardEntityMapper();
	}
}
