package com.br.clean.arch.customer.domain.entitie.custommer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.clean.arch.address.domain.entitie.address.Charge;
import com.br.clean.arch.address.domain.entitie.address.Delivery;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

public class Customer {

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
	
	private List<Delivery> deliveries = new ArrayList<Delivery>();
	private List<Charge> charges = new ArrayList<Charge>();
	
	public Customer(String cpf, String name,
					 LocalDate birth, String password,
					 String confirmPassword, Gender gender,
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
	
	public void addNewDelivery(Delivery delivery) {
		this.deliveries.add(delivery);
	}
	
	public void addNewCharge(Charge charge) {
		this.charges.add(charge);
	}
	
	private void checkCharacterQuantity(String password) {
		if(password == null || password.length() < LENGHT_PASSWORD) {
	        throw new IllegalArgumentException("The password requires at least 8 characters to be valid");
		}
	}

	private void matchPasswordAndConfirmPassword(String password, String confirmPassword) {
		if(password == null || !password.equals(confirmPassword)) {
			throw new IllegalArgumentException("Password and confirm password not match");
		}
	}
	
	public List<Charge> getCharges() {
		return charges;
	}
	
	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
	    String regexCpf = "^\\d{11}$";
		if(cpf == null || !cpf.matches(regexCpf)) {
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
		if(birth == null || birth.isAfter(LocalDate.now().minusYears(18))) {
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

	@Override
	public String toString() {
		return "Custommer [cpf=" + cpf + ", active=" + active + ", name=" + name + ", birth=" + birth
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", gender=" + gender + ", phone="
				+ phone + ", email=" + email + "]";
	}
}
