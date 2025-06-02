package com.br.clean.arch.custommer.card;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.card.buider.CardBuilder;
import com.br.clean.arch.domain.entitie.card.exeptions.CardNotFoundException;
import com.br.clean.arch.domain.entitie.card.valueObject.Flag;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerBuilderCardTest {

	@Test
	public void mustCreateCardWithBuilder() {
		
		CardBuilder builder = new CardBuilder();
	    Customer customer = new Customer(
	        "15624874140",
	        "kleberson",
	        LocalDate.of(1994, 10, 5),
	        "89724521GOLvi1@",
	        Gender.MALE,
	        new Phone("22", "123456789"),
	        new Email("lordlof@gmail.com")
	    );

	    // Config the Customer at CustomerBuilder
	    builder.WithCard(customer);

	    customer = builder.builderCard(true, "kleberson santos",
	    							  "154", "6224758047501598",
	    							  Flag.MASTERCARD,
	    							  LocalDate.of(2040, 10, 10));

	    Assertions.assertEquals(true, customer.getCards().get(0).isMain());
	    Assertions.assertEquals("kleberson santos", customer.getCards().get(0).getPrintedName());
	    Assertions.assertEquals("154", customer.getCards().get(0).getCode());
	    Assertions.assertEquals("6224758047501598", customer.getCards().get(0).getNumberCard());
	    Assertions.assertEquals(Flag.MASTERCARD, customer.getCards().get(0).getFlag());
	    Assertions.assertEquals(LocalDate.of(2040, 10, 10), customer.getCards().get(0).getExpirationDate());
	}
	
	
	//Invalit data for builder
	@Test
	public void ShouldNotAcceptInvalidaCardNumber() {
		Assertions.assertThrows(CardNotFoundException.class,
				() -> new Card(true, "kleberson santos",
				  "154", "6224758047501577",
				  Flag.MASTERCARD,
				  LocalDate.of(2040, 10, 10)));
	}
	
	@Test
	public void ShouldNotAcceptInvalidaCardCode() {
		Assertions.assertThrows(CardNotFoundException.class,
				() -> new Card(true, "kleberson santos",
				  "15", "4551627971196279",
				  Flag.VISA,
				  LocalDate.of(2040, 10, 10)));
	}
	
	@Test
	public void ShouldNotAcceptInvalidaCardDate() {
		Assertions.assertThrows(CardNotFoundException.class,
				() -> new Card(true, "kleberson santos",
				  "150", "4551627971196279",
				  Flag.VISA,
				  LocalDate.of(2022, 10, 10)));
	}
}
