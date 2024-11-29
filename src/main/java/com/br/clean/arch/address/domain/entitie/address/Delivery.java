package com.br.clean.arch.address.domain.entitie.address;

public class Delivery extends Address {

	private String deliveryPhrase;

	public Delivery(String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
		super(receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city);
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public Delivery(String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city, String deliveryPhrase) {
		super(receiver, street, number, neighborhood, cep, streetType, typeResidence, city);
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
