package com.br.clean.arch.domain.entitie.customer.valueObject;

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

	public void setDdd(String ddd) {
		
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public void setPhone(String phone) {
		
	}
	
	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Phone [ddd=" + ddd + ", phone=" + phone + "]";
	}
}
