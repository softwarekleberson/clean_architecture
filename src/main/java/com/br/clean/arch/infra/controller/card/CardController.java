package com.br.clean.arch.infra.controller.card;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<CardListDto> createCard(
	        @PathVariable String cpf, 
	        @RequestBody @Valid CardDto dto) {
	    
	    Customer customer = getCustomer.getCustomerByCpf(cpf);
	    
	    customer.addNewCard(new Card(
	            dto.main(),
	            dto.printedName(),
	            dto.code(),
	            dto.numberCard(),
	            dto.flag(),
	            dto.expirationDate()
	    ));
	    
	    Card card = createCard.createCard(cpf, new Card(
	            dto.main(),
	            dto.printedName(),
	            dto.code(),
	            dto.numberCard(),
	            dto.flag(),
	            dto.expirationDate()
	    ));

	    CardListDto responseDto = new CardListDto(card.getId(), card.getNumberCard(), card.getCode());

	    return ResponseEntity
	            .created(URI.create("/card/" + card.getId())) 
	            .body(responseDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<CardListDto>> listAllCards(
	        @PathVariable String id, 
	        @PageableDefault(size = 10) Pageable pageable) {
	    
	    List<Card> allCards = listCard.listCards(id);

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allCards.size());
	    
	    if (start >= allCards.size()) {
	        return ResponseEntity.noContent().build(); 
	    }

	    List<CardListDto> paginatedCards = allCards.subList(start, end)
	            .stream()
	            .map(card -> new CardListDto(card.getId(), card.getNumberCard(), card.getCode()))
	            .toList();

	    Page<CardListDto> page = new PageImpl<>(paginatedCards, pageable, allCards.size());

	    return ResponseEntity.ok(page); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
		try {
			deleteCard.deleteCard(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
