package com.br.clean.arch.infra.persistence.address.delivery;

import com.br.clean.arch.infra.persistence.customer.CustomerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliverys")
public class DeliveryEntity extends AddressEntity{
	
	private String deliveryPhrase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerEntity_id")
	private CustomerEntity customerEntity;

	public DeliveryEntity(String deliveryPhrase) {
		super();
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}
	
	public void setDeliveryPhrase(String deliveryPhrase) {
		this.deliveryPhrase = deliveryPhrase;
	}
}
