package com.br.clean.arch.infra.controller.card;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.card.CreateCard;
import com.br.clean.arch.application.usecases.card.DeleteCard;
import com.br.clean.arch.application.usecases.card.ListCard;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/card")
public class CardController {

	private final GetCustomer getCustomer;
	private final CreateCard createCard;
	private final ListCard listCard;
	private final DeleteCard deleteCard;
	
	public CardController(GetCustomer getCustomer, CreateCard createCard, ListCard listCard, DeleteCard deleteCard) {
		this.createCard = createCard;
		this.getCustomer = getCustomer;
		this.listCard = listCard;
		this.deleteCard = deleteCard;
	}
	
	@PostMapping("/{cpf}")
	public CardListDto createCard(@PathVariable String cpf, @RequestBody @Valid CardDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		customer.addNewCard(new Card(dto.main(), dto.printedName(), dto.code(), dto.numberCard(), dto.flag(), dto.expirationDate()));
		Card card = createCard.createCard(cpf, new Card(dto.main(), dto.printedName(), dto.code(), dto.numberCard(), dto.flag(), dto.expirationDate()));
		return new CardListDto(card.getNumberCard(), card.getCode());
	}
	
	@GetMapping("/{id}")
	public List<CardListDto> listAllCards(@PathVariable String id){
		return listCard.listCards(id).
			   stream().
			   map(u -> new CardListDto(u.getNumberCard(), u.getCode())).
			   collect(Collectors.toList());
	}
	
	@DeleteMapping("/{id}")
	public void deleteCard(@PathVariable Long id) {
		deleteCard.deleteCard(id);
	}
}
