package com.br.clean.arch.infra.controller.card;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.card.CreateCard;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {

	private final GetCustomer getCustomer;
	private final CreateCard createCard;
	
	public CardController(GetCustomer getCustomer, CreateCard createCard) {
		this.createCard = createCard;
		this.getCustomer = getCustomer;
	}
	
	@PostMapping("/{cpf}")
	public CardListDto createCard(@PathVariable String cpf, @RequestBody @Valid CardDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		customer.addNewCard(new Card(dto.main(), dto.printedName(), dto.code(), dto.numberCard(), dto.flag(), dto.expirationDate()));
		Card card = createCard.createCard(cpf, new Card(dto.main(), dto.printedName(), dto.code(), dto.numberCard(), dto.flag(), dto.expirationDate()));
		return new CardListDto(card.getNumberCard(), card.getCode());
	}
}
