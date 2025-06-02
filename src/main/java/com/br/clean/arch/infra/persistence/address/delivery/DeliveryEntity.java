package com.br.clean.arch.infra.persistence.address.delivery;

import com.br.clean.arch.infra.persistence.customer.CustomerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveries")
public class DeliveryEntity extends AddressEntity{
	
	
	private String deliveryPhrase;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_entity_id") 
	private CustomerEntity customerEntity;
	
	public DeliveryEntity(Boolean main, String receiver, String street, String number, String neighborhood, String cep,
			String observation, String streetType, String typeResidence, String city, String deliveryPhrase, String state, String country) {
		super(main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city, state, country);
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public DeliveryEntity() {
        super();
    }
	
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}
	
	public void setDeliveryPhrase(String deliveryPhrase) {
		this.deliveryPhrase = deliveryPhrase;
	}
}
