package com.br.clean.arch.domain.entitie.valueObject;

import java.util.Objects;

import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;

public final class Email {

	private final String email;
	
	public Email(String email) {
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if(email == null || !email.matches(emailRegex)) {
			throw new IncorretEmailException("Format email incoret.");
		}
        this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Email [email=" + email + "]";
	}
}
