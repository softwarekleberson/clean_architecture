package com.br.clean.arch.address.domain.entitie.address.builder;

import com.br.clean.arch.address.domain.entitie.address.Delivery;
import com.br.clean.arch.customer.domain.entitie.custommer.Customer;

public class DeliveryBuilder {

	private Customer customer;
	
	public DeliveryBuilder withCustomer(Customer customer) {
	        this.customer = customer;
	        return this;
	}
	
	public Customer builderDeliveryWitchObservation(String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewDelivery(new Delivery(receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
	
	public Customer builderDeliverywithoutObservation(String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
				
		this.customer.addNewDelivery(new Delivery(receiver, street, number, neighborhood, cep, streetType, typeResidence, city, deliveryPhrase));
		return customer;
	}
}