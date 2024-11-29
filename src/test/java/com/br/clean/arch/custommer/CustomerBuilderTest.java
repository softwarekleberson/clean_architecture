package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.customer.domain.entitie.custommer.Customer;
import com.br.clean.arch.customer.domain.entitie.custommer.builder.CustomerBuilder;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

class CustomerBuilderTest {

	 	@Test
	    public void mustCreateCustomerWithBuilder() {
	        CustomerBuilder builder = new CustomerBuilder();
	        Customer customer = builder.builderCustomer(
	            "15624874140",
	            "kleberson",
	            LocalDate.of(1994, 10, 5),
	            "89724521GOLvi1@",
	            "89724521GOLvi1@",
	            Gender.MASCULINO,
	            new Phone("22", "123456789"),
	            new Email("lordlof@gmail.com")
	        );

	        Assertions.assertEquals("15624874140", customer.getCpf());
	        Assertions.assertEquals("kleberson", customer.getName());
	        Assertions.assertEquals(LocalDate.of(1994, 10, 5), customer.getBirth());
	        Assertions.assertEquals("89724521GOLvi1@", customer.getPassword());
	        Assertions.assertEquals("89724521GOLvi1@", customer.getConfirmPassword());
	        Assertions.assertEquals(Gender.MASCULINO, customer.getGender());
	        Assertions.assertEquals("22", customer.getPhone().getDdd());
	        Assertions.assertEquals("123456789", customer.getPhone().getPhone());
	        Assertions.assertEquals("lordlof@gmail.com", customer.getEmail().getEmail());
	    }
}