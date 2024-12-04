package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.address.builder.ChargeBuilder;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerBuilderChargeTest {

	@Test
	public void mustCreateChargeWitchBuilder() {
		
		ChargeBuilder builder = new ChargeBuilder();
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

	    // Config the Customer at CustomerBuilder
	    builder.withCustomer(customer);

	    customer = builder.builderCharge(
	        "jose silva", "rua das flores", "100", "vila belmiro", "12345-700",
	        "entregar as 10 horas", "rua", "casa", "São jose"
	    );

	    Assertions.assertEquals("jose silva", customer.getCharges().get(0).getReceiver());
	    Assertions.assertEquals("rua das flores", customer.getCharges().get(0).getStreet());
	    Assertions.assertEquals("100", customer.getCharges().get(0).getNumber());
	    Assertions.assertEquals("vila belmiro", customer.getCharges().get(0).getNeighborhood());
	    Assertions.assertEquals("12345-700", customer.getCharges().get(0).getCep());
	    Assertions.assertEquals("entregar as 10 horas", customer.getCharges().get(0).getObservation());
	    Assertions.assertEquals("rua", customer.getCharges().get(0).getStreetType());
	    Assertions.assertEquals("casa", customer.getCharges().get(0).getTypeResidence());
	    Assertions.assertEquals("São jose", customer.getCharges().get(0).getCity());       
	}
}
