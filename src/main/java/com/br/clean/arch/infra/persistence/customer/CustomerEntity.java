package com.br.clean.arch.infra.persistence.customer;

import java.time.LocalDate;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Email;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Gender;
import com.br.clean.arch.customer.domain.entitie.custommer.valueObject.Phone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	
	public static final int LENGHT_PASSWORD = 8;
	private String password;
	
	@Transient
	private String confirmPassword;
	private Gender gender;
	private Phone phone;
	private Email email;
		
	public CustomerEntity() {
	}
	
	public CustomerEntity(String cpf, String name,
					 LocalDate birth, String password,
					 String confirmPassword, Gender gender,
					 Phone phone, Email email) {
						
		this.active = false;
		this.cpf = cpf;
		this.birth = birth;
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
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
