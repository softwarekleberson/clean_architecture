package com.br.clean.arch.customer.domain.entitie.custommer;

import java.time.LocalDate;

import com.br.clean.arch.address.domain.entitie.address.Delivery;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

public class BuilderCustomer {

	private Customer customer;
	
	public Customer builderCustomer(String cpf, String name,
			 						LocalDate birth,
			 						String password,
			 						String confirmPassword,
			 						Gender gender,
			 						Phone phone,
			 						Email email) {
		
		 this.customer = new Customer(cpf, name, birth, password, confirmPassword, gender, phone, email);
		 return customer;
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
