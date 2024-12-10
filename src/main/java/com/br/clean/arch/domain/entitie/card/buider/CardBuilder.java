package com.br.clean.arch.domain.entitie.card.buider;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.valueObject.Flag;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class CardBuilder {

	private Customer customer;
	
	public CardBuilder witchCard(Customer customer) {
		this.customer = customer;
		return this;
	}
	
	public Customer builderCard(boolean main, String printedName,
								String code, String numberCard,
								Flag flag, LocalDate expirationDate) {
		
		this.customer.addNewCard(new Card(main, printedName, code, numberCard, flag, expirationDate));
		return customer;
	}
}
