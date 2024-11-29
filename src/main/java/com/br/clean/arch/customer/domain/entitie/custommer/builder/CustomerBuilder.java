package com.br.clean.arch.customer.domain.entitie.custommer.builder;

import java.time.LocalDate;

import com.br.clean.arch.customer.domain.entitie.custommer.Customer;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

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
