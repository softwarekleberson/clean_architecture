package com.br.clean.arch.domain.entitie.valueObject;

import java.util.Objects;

import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretPhoneException;

public final class Phone {

	public static final int FORMAT_DDD = 2;
	private final String ddd;
	
	public static final int FORMAT_PHONE = 9;
	private final String phone;
	
	public Phone(String ddd, String phone) {
		
		if(ddd == null || ddd.length() != FORMAT_DDD) {
			throw new IncorretPhoneException("Format DDD incorrect");
		}
		
		this.ddd = ddd;
		
		if(phone == null || phone.length() != FORMAT_PHONE) {
			throw new IncorretPhoneException("Format phone incorrect");
		}
		this.phone = phone;
	}

	public String getDdd() {
		return ddd;
	}
	
	public String getPhone() {
		return phone;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ddd, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(ddd, other.ddd) && Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "Phone [ddd=" + ddd + ", phone=" + phone + "]";
	}
}
