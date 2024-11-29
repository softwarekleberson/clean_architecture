package com.br.clean.arch.customer.domain.entitie.custommer.valueObject;

import com.br.clean.arch.customer.domain.entitie.custommer.exceptions.IncorretEmailException;

public class Email {

	private String email;
	
	public Email(String email) {
		setEmail(email);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if(!email.matches(emailRegex)) {
			System.out.println("oi");
			throw new IncorretEmailException("Format email incoret.");
		}
        this.email = email;
	}
}
