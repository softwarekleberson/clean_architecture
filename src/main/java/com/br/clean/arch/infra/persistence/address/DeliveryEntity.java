package com.br.clean.arch.infra.persistence.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliverys")
public class DeliveryEntity extends AddressEntity{
	
	private String deliveryPhrase;

	public DeliveryEntity(String deliveryPhrase) {
		super();
		this.deliveryPhrase = deliveryPhrase;
	}
	
}
