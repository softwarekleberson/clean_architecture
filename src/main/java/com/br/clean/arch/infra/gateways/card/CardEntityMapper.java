package com.br.clean.arch.infra.gateways.card;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.valueObject.Flag;
import com.br.clean.arch.infra.persistence.card.CardEntity;
import com.br.clean.arch.infra.persistence.card.FlagEntity;

public class CardEntityMapper {

	public CardEntity toEntity(Card domain) {
		if(domain == null) {
			return null;
		}
		
		return new CardEntity(
				domain.isMain(),
				domain.getPrintedName(),
				domain.getCode(),
				domain.getNumberCard(),
				toEntity(domain.getFlag()),
				domain.getExpirationDate()
		);
	}
	
	public Card toDomain(CardEntity entity) {
		if(entity == null) {
			return null;
		}
		
		return new Card(
				entity.getId(),
				entity.isMain(),
				entity.getPrintedName(),
				entity.getCode(),
				entity.getNumberCard(),
				toDomain(entity.getFlag()),
				entity.getExpirationDate()
				);
	}

	private static Flag toDomain(FlagEntity entity) {
		return entity == null ? null : Flag.valueOf(entity.name());
	}

	private static FlagEntity toEntity(Flag domain) {
		return domain == null ? null : FlagEntity.valueOf(domain.name());
	}
}
