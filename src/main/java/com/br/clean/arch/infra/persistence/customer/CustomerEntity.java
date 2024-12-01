package com.br.clean.arch.infra.persistence.customer;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Enumerated(EnumType.STRING)
	private GenderEntity genderEntity;
	
	@Embedded
	private PhoneEntity phoneEntity;
	
	@Embedded
	private EmailEntity emailEntity;
		
	public CustomerEntity() {
	}
	
	public CustomerEntity(String cpf, String name,
					 LocalDate birth, String password,
					 GenderEntity genderEntity,
					 PhoneEntity phoneEntity, EmailEntity emailEntity) {
						
		this.active = false;
		this.cpf = cpf;
		this.birth = birth;
		this.name = name;
		this.password = password;
		this.genderEntity = genderEntity;
		this.phoneEntity = phoneEntity;
		this.emailEntity = emailEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public GenderEntity getGenderEntity() {
		return genderEntity;
	}

	public void setGenderEntity(GenderEntity genderEntity) {
		this.genderEntity = genderEntity;
	}

	public PhoneEntity getPhoneEntity() {
		return phoneEntity;
	}

	public void setPhoneEntity(PhoneEntity phoneEntity) {
		this.phoneEntity = phoneEntity;
	}

	public EmailEntity getEmailEntity() {
		return emailEntity;
	}

	public void setEmailEntity(EmailEntity emailEntity) {
		this.emailEntity = emailEntity;
	}
}
