package com.br.clean.arch.custommer.domain.entities.custommer.valueObject;

import com.br.clean.arch.custommer.domain.entities.custommer.exceptions.IncorretEmail;

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
		if(!emailRegex.matches(email)) {
			throw new IncorretEmail("Format email incoret.");
		}
        this.email = email;
	}
}
