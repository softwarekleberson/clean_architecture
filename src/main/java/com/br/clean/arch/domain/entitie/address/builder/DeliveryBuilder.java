package com.br.clean.arch.domain.entitie.address.builder;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;

public class DeliveryBuilder {

	private Customer customer;
	
	public DeliveryBuilder withCustomer(Customer customer) {
	        this.customer = customer;
	        return this;
	}
	
	public Customer builderDeliveryWitchObservation(Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewDelivery(new Delivery(main, street, number, neighborhood, cep, observation, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
	
	public Customer builderDeliverywithoutObservation(Boolean main, String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewDelivery(new Delivery(main, receiver, street, number, neighborhood, cep, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
}
