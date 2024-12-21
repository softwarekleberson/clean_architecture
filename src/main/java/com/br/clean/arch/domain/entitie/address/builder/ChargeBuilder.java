package com.br.clean.arch.domain.entitie.address.builder;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class ChargeBuilder {

	private Customer customer;
	
	public ChargeBuilder withCustomer(Customer customer) {
	        this.customer = customer;
	        return this;
	}
	
	public Customer builderCharge(Boolean main, String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewCharge(new Charge(main, receiver, street, number, neighborhood, cep, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
}
