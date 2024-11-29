package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.customer.domain.entitie.custommer.Customer;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

class CustomerApplicationCpf {

	@Test
	public void ShouldNotAceptNewCustommerwitchIncorrectCpf() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Customer
				("417.625.971-80",
				"kleberson",
				 LocalDate.now().minusYears(18).minusDays(1),
				"123VAIjava@",
				"123VAIjava@",
				Gender.MASCULINO,
				new Phone("11", "123456789"),
				new Email("santossilvakleberson@gmail.com")));
		
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Customer
				("417625971-80",
				"kleberson",
				 LocalDate.now().minusYears(18).minusDays(1),
				"123VAIjava@",
				"123VAIjava@",
				Gender.MASCULINO,
				new Phone("11", "123456789"),
				new Email("santossilvakleberson@gmail.com")));
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Customer
				("4176259718",
				"kleberson",
				 LocalDate.now().minusYears(18).minusDays(1),
				"123VAIjava@",
				"123VAIjava@",
				Gender.MASCULINO,
				new Phone("11", "123456789"),
				new Email("santossilvakleberson@gmail.com")));
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Customer
				("417625971811",
				"kleberson",
				 LocalDate.now().minusYears(18).minusDays(1),
				"123VAIjava@",
				"123VAIjava@",
				Gender.MASCULINO,
				new Phone("11", "123456789"),
				new Email("santossilvakleberson@gmail.com")));
	}	
}
