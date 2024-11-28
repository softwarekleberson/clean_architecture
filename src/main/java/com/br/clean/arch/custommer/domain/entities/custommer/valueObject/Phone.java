package com.br.clean.arch.custommer.domain.entities.custommer.valueObject;

import com.br.clean.arch.custommer.domain.entities.custommer.exceptions.IncorretPhone;

public class Phone {

	public static final int FORMAT_DDD = 2;
	private String ddd;
	
	public static final int FORMAT_PHONE = 9;
	private String phone;
	
	public Phone(String ddd, String phone) {
		setDdd(ddd);
		setPhone(phone);
	}
	
	public void setDdd(String ddd) {
		if(ddd.length() > FORMAT_DDD) {
			throw new IncorretPhone("Format DDD incorrect");
		}
		this.ddd = ddd;
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public void setPhone(String phone) {
		if(phone.length() > FORMAT_PHONE) {
			throw new IncorretPhone("Format phone incorrect");
		}
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
}
