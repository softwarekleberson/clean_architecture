package com.br.clean.arch.address.domain.entitie.address.builder;

import com.br.clean.arch.address.domain.entitie.address.Charge;
import com.br.clean.arch.customer.domain.entitie.custommer.Customer;

public class ChargeBuilder {

	private Customer customer;
	
	public ChargeBuilder withCustomer(Customer customer) {
	        this.customer = customer;
	        return this;
	}
	
	public Customer builderCharge(String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewCharge(new Charge(receiver, street, number, neighborhood, cep, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
}
