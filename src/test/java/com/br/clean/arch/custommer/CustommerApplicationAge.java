package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.clean.arch.customer.domain.entitie.custommer.Customer;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

@SpringBootTest
class CustommerApplicationAge {

	@Test
	public void ShouldNotAceptNewCustommerwitchUnderage() {
	    Assertions.assertThrows(IllegalArgumentException.class, 
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(17).plusDays(1), // Menor de idade
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MASCULINO,
	            new Phone("11", "123456789"),
	            new Email("santossilvakleberson@gmail.com")
	        ));	    
	}
}