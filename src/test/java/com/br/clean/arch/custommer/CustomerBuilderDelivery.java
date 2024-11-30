package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.address.domain.entitie.address.builder.DeliveryBuilder;
import com.br.clean.arch.customer.domain.entitie.custommer.Customer;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

class CustomerBuilderDelivery {

	@Test
	public void mustCreateDeliveryWitchBuilder() {
		
		DeliveryBuilder builder = new DeliveryBuilder();
	    Customer customer = new Customer(
	        "15624874140",
	        "kleberson",
	        LocalDate.of(1994, 10, 5),
	        "89724521GOLvi1@",
	        "89724521GOLvi1@",
	        Gender.MASCULINO,
	        new Phone("22", "123456789"),
	        new Email("lordlof@gmail.com")
	    );

	    // Configurando o Customer no DeliveryBuilder
	    builder.withCustomer(customer);

	    customer = builder.builderDeliveryWitchObservation(
	        "jose silva", "rua das flores", "100", "vila belmiro", "12345-700",
	        "entregar as 10 horas", "rua", "casa", "São jose", "entregar na portaria"
	    );

	    Assertions.assertEquals("jose silva", customer.getDeliveries().get(0).getReceiver());
	    Assertions.assertEquals("rua das flores", customer.getDeliveries().get(0).getStreet());
	    Assertions.assertEquals("100", customer.getDeliveries().get(0).getNumber());
	    Assertions.assertEquals("vila belmiro", customer.getDeliveries().get(0).getNeighborhood());
	    Assertions.assertEquals("12345-700", customer.getDeliveries().get(0).getCep());
	    Assertions.assertEquals("entregar as 10 horas", customer.getDeliveries().get(0).getObservation());
	    Assertions.assertEquals("rua", customer.getDeliveries().get(0).getStreetType());
	    Assertions.assertEquals("casa", customer.getDeliveries().get(0).getTypeResidence());
	    Assertions.assertEquals("São jose", customer.getDeliveries().get(0).getCity());
	    Assertions.assertEquals("entregar na portaria", customer.getDeliveries().get(0).getDeliveryPhrase());
       
	}
}
