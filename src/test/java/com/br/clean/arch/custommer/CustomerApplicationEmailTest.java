package com.br.clean.arch.custommer;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

class CustomerApplicationEmailTest {

	@Test
	public void shouldNotAcceptNewCustomerWithIncorrectIncorretEmailException() {
	    Assertions.assertThrows(IncorretEmailException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MASCULINO,
	            new Phone("11", "123456789"),
	            new Email("@gmail.com")
	        ),
	        
	        "Should throw IncorretEmailException when creating client."
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithWithoutAt() {
	    Assertions.assertThrows(IncorretEmailException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MASCULINO,
	            new Phone("11", "123456789"),
	            new Email("emailgmail.com")
	        ),
	        
	        "Should throw IncorretEmailException when creating client."
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithWithoutIdentify() {
	    Assertions.assertThrows(IncorretEmailException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MASCULINO,
	            new Phone("11", "123456789"),
	            new Email("email@.com")
	        ),
	        
	        "Should throw IncorretEmailException when creating client."
	    );
	}
	
	@Test
	public void shouldNotAcceptNewCustomerWithWithoutCom() {
	    Assertions.assertThrows(IncorretEmailException.class,
	        () -> new Customer(
	            "41762597180",
	            "kleberson",
	            LocalDate.now().minusYears(18).minusDays(1),
	            "123VAIjava@",
	            "123VAIjava@",
	            Gender.MASCULINO,
	            new Phone("11", "123456789"),
	            new Email("email@gmail.")
	        ),
	        
	        "Should throw IncorretEmailException when creating client."
	    );
	}
}
