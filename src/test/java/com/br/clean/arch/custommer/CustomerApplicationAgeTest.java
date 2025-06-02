package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.UnderageException;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerApplicationAgeTest {

	@Test
	public void ShouldNotAceptNewCustommerwitchUnderage() {
	    Assertions.assertThrows(UnderageException.class, 
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(17).plusDays(1), // Menor de idade
	            "123VAIjava@",
	            Gender.MALE,
	            new Phone("11", "123456789"),
	            new Email("santossilvakleberson@gmail.com")
	        ));	    
	}
}