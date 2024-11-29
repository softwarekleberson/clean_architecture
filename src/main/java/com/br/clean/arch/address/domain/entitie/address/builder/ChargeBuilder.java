package com.br.clean.arch.address.domain.entitie.address.builder;

import com.br.clean.arch.address.domain.entitie.address.Delivery;
import com.br.clean.arch.customer.domain.entitie.custommer.Customer;

public class ChargeBuilder {

	private Customer customer;
	
	public Customer builderCharge(String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewDelivery(new Delivery(receiver, street, number, neighborhood, cep, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
}
