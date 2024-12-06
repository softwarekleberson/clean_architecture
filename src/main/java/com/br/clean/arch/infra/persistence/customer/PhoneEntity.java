package com.br.clean.arch.infra.persistence.customer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class PhoneEntity {

	public static final int FORMAT_DDD = 2;
	private String ddd;
	
	public static final int FORMAT_PHONE = 9;
	private String phone;
	
	public PhoneEntity() {
	}
	
	public PhoneEntity(String ddd, String phone) {
		this.ddd = ddd;
		this.phone = phone;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
