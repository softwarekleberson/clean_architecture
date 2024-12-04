package com.br.clean.arch.domain.entitie.customer.builder;

import java.time.LocalDate;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;

public class CustomerBuilder {

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
}
