package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretPhoneException;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerApplicationPhoneTest {

	@Test
	public void shouldNotAcceptNewCustomerWithIncorrectDddPhone() {
	    Assertions.assertThrows(IncorretPhoneException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MALE,
	            new Phone("1", "123456789"),
	            new Email("santossilvakleberson@gmail.com")
	        ),
	        
	        "Should throw IncorrectPhoneException when creating client Format DDD incorrect"
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithIncorrectLeMoreDddPhone() {
	    Assertions.assertThrows(IncorretPhoneException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MALE,
	            new Phone("111", "123456789"),
	            new Email("santossilvakleberson@gmail.com")
	        ),
	        
	        "Should throw IncorrectPhoneException when creating client Format DDD incorrect"
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithIncorrectPhone() {
	    Assertions.assertThrows(IncorretPhoneException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MALE,
	            new Phone("11", "12345678"),
	            new Email("santossilvakleberson@gmail.com")
	        ),
	        
	        "Should throw IncorrectPhoneException when creating client Format phone incorrect"
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithIncorrectMorePhone() {
	    Assertions.assertThrows(IncorretPhoneException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MALE,
	            new Phone("11", "1234567897"),
	            new Email("santossilvakleberson@gmail.com")
	        ),
	        
	        "Should throw IncorrectPhoneException when creating client Format phone incorrect"
	    );
	}
	
}
