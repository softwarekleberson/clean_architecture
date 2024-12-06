package com.br.clean.arch.infra.persistence.customer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class EmailEntity {

	private String email;
	
	public EmailEntity() {
	}
	
	public EmailEntity(String email) {
		this.email = email;
	}
	
	public void setEmail(String email) { 
		this.email = email.trim().toLowerCase();
	}
}
