package com.br.clean.arch.custommer.domain.entities.custommer;

import java.time.LocalDate;
import java.util.UUID;

import com.br.clean.arch.custommer.domain.entities.custommer.valueObject.Email;
import com.br.clean.arch.custommer.domain.entities.custommer.valueObject.Gender;
import com.br.clean.arch.custommer.domain.entities.custommer.valueObject.Phone;

public class Custommer {

	private UUID id = UUID.randomUUID();
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	
	public static final int LENGHT_PASSWORD = 8;
	private String password;
	private String confirmPassword;
	private Gender gender;
	private Phone phone;
	private Email email;
	
	public Custommer(String cpf, String name,
					 LocalDate birth, String password, String confirmPassword, Gender gender,
					 Phone phone, Email email) {
		
		setCpf(cpf);
		setBirth(birth);
		
		matchPasswordAndConfirmPassword(password, confirmPassword);
		checkCharacterQuantity(password);
		
		this.active = false;
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}
	
	private void checkCharacterQuantity(String password) {
		if(password.length() < LENGHT_PASSWORD) {
			throw new IllegalArgumentException("The password request 8 caracters for be valid");
		}
	}

	private void matchPasswordAndConfirmPassword(String password, String confirmPassword) {
		if(!password.matches(confirmPassword)) {
			throw new IllegalArgumentException("Password and confirm password not match");
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		String regexCpf = "^\\d{11}$";
		if(!regexCpf.matches(cpf)) {
			throw new IllegalArgumentException("Cpf does not match the required format");
		}
		this.cpf = cpf;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		if(birth.isAfter(LocalDate.now().minusYears(18))) {
			throw new IllegalArgumentException("For register you need 18 years");
		}
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
}
