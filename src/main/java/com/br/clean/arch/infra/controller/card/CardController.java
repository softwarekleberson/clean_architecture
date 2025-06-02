package com.br.clean.arch.infra.controller.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.clean.arch.application.usecases.card.CreateCard;
import com.br.clean.arch.application.usecases.card.DeleteCard;
import com.br.clean.arch.application.usecases.card.ListCard;
import com.br.clean.arch.application.usecases.card.dto.input.CreateCardCommand;
import com.br.clean.arch.application.usecases.card.dto.output.CardOutputDto;
import com.br.clean.arch.application.usecases.customer.GetCustomerById;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customer/cards")
@SecurityRequirement(name = "bearer-key")
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final GetCustomerById getCustomerByIdUseCase;
    private final CreateCard createCardUseCase;
    private final ListCard listCardUseCase;
    private final DeleteCard deleteCardUseCase;

    public CardController(CreateCard createCardUseCase, ListCard listCardUseCase, DeleteCard deleteCardUseCase, GetCustomerById getCustomerByIdUseCase) {
        this.createCardUseCase = createCardUseCase;
        this.listCardUseCase = listCardUseCase;
        this.deleteCardUseCase = deleteCardUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CardOutputDto> createCard(
            @RequestBody @Valid CreateCardCommand dto,
            Authentication authentication) {

        String customerId = authentication.getName();
        logger.info("Iniciando tentativa de criação de cartão para o cliente ID: {}", customerId);

        CustomerOutputDto customer = getCustomerByIdUseCase.getCustomerById(customerId);
        CardOutputDto createdCard = createCardUseCase.createCard(customer.id(), dto);

        logger.info("Cartão criado com sucesso para o cliente ID: {}. ID do cartão: {}", customerId, createdCard.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }

    @GetMapping
    public ResponseEntity<Page<CardOutputDto>> listAllCards(
            Authentication authentication,
            @PageableDefault(size = 5) Pageable pageable) {

        String customerId = authentication.getName();
        logger.info("Iniciando tentativa de listagem de cartões para o cliente ID: {}", customerId);

        CustomerOutputDto customer = getCustomerByIdUseCase.getCustomerById(customerId);
        Page<CardOutputDto> cardsPage = listCardUseCase.listCards(customer.id(), pageable);

        logger.info("Listagem de cartões bem-sucedida para o cliente ID: {}", customerId);
        return ResponseEntity.ok(cardsPage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        logger.info("Iniciando tentativa de exclusão do cartão com ID: {}", id);

        deleteCardUseCase.deleteCard(id);

        logger.info("Cartão com ID {} excluído com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
}
