package com.br.clean.arch.application.gateways.card;

import java.util.List;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.infra.controller.card.CardUpdateDto;

public interface RepositoryCard {

	List<Card> listCard (String id);
	Card createNewCard (String id, Card card);
	Card updateCard (String id, CardUpdateDto dto);
	Card deleteCard (Long id);
}
