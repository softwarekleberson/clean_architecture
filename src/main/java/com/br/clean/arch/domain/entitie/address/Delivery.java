package com.br.clean.arch.domain.entitie.address;

public class Delivery extends Address {

	private String deliveryPhrase;

	public Delivery(Long id, boolean main, String receiver, String street, String number, String neighborhood,
			String cep, String observation, String streetType, String typeResidence, String city, String state,
			String country, String deliveryPhrase) {
		super(id, main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city,
				state, country);
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public Delivery(boolean main, String receiver, String street,
			String number, String neighborhood, String cep,
			String observation, String streetType,
			String typeResidence, String city, String state,
			String country, String deliveryPhrase) {
		super(main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city, state,
				country);
		this.deliveryPhrase = deliveryPhrase;
	}

	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}
	
	public void setDeliveryPhrase(String deliveryPhrase) {
		this.deliveryPhrase = deliveryPhrase;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryPhrase=" + deliveryPhrase + "]";
	}
}
