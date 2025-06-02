package com.br.clean.arch.custommer.delivery;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.address.builder.DeliveryBuilder;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerBuilderDeliveryTest {

	@Test
	public void mustCreateDeliveryWithBuilder() {
		
		DeliveryBuilder builder = new DeliveryBuilder();
	    Customer customer = new Customer(
	        "15624874140",
	        "kleberson",
	        LocalDate.of(1994, 10, 5),
	        "89724521GOLvi1@",
	        Gender.MALE,
	        new Phone("22", "123456789"),
	        new Email("lordlof@gmail.com")
	    );

	    // Config the Customer at DeliveryBuilder
	    builder.withCustomer(customer);

	    customer = builder.builderDeliveryWitchObservation(
	    				  true, "kleber", "rua três",
	    				  "100", "vila", "12345-300",
	    				  "xxxx", "rua", "casa",
	    				  "mogi", "ai", "são paulo", "Brasil");
	    
	    Assertions.assertEquals("kleber", customer.getDeliveries().get(0).getReceiver());
	    Assertions.assertEquals("rua três", customer.getDeliveries().get(0).getStreet());
	    Assertions.assertEquals("100", customer.getDeliveries().get(0).getNumber());
	    Assertions.assertEquals("vila", customer.getDeliveries().get(0).getNeighborhood());
	    Assertions.assertEquals("12345-300", customer.getDeliveries().get(0).getCep());
	    Assertions.assertEquals("xxxx", customer.getDeliveries().get(0).getObservation());
	    Assertions.assertEquals("rua", customer.getDeliveries().get(0).getStreetType());
	    Assertions.assertEquals("casa", customer.getDeliveries().get(0).getTypeResidence());
	    Assertions.assertEquals("mogi", customer.getDeliveries().get(0).getCity());
	    Assertions.assertEquals("ai", customer.getDeliveries().get(0).getDeliveryPhrase());
	    Assertions.assertEquals("são paulo", customer.getDeliveries().get(0).getState());
	    Assertions.assertEquals("Brasil", customer.getDeliveries().get(0).getCountry());
	}
}
