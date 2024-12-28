package com.br.clean.arch.infra.persistence.customer;

import java.time.LocalDate;
import java.util.List;

import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;
import com.br.clean.arch.infra.persistence.card.CardEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private GenderEntity gender;
	
	@Embedded
	private PhoneEntity phone;
	
	@Embedded
	private EmailEntity email;
	
	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DeliveryEntity> delivery;
	
	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChargeEntity> charges;
	
	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CardEntity> card;
		
	public CustomerEntity() {
	}
	
	public CustomerEntity(String cpf, String name,
					 LocalDate birth, String password,
					 GenderEntity gender,
					 PhoneEntity phone, EmailEntity email) {
						
		this.active = false;
		this.cpf = cpf;
		this.birth = birth;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}
	
	public CustomerEntity(String id, String cpf, String name,
			 LocalDate birth, String password,
			 GenderEntity gender,
			 PhoneEntity phone, EmailEntity email) {
		
		this.id = id;
		this.active = false;
		this.cpf = cpf;
		this.birth = birth;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<DeliveryEntity> getDelivery() {
		return delivery;
	}

	public List<ChargeEntity> getCharges() {
		return charges;
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

	public GenderEntity getGenderEntity() {
		return gender;
	}

	public void setGenderEntity(GenderEntity gender) {
		this.gender = gender;
	}

	public PhoneEntity getPhoneEntity() {
		return phone;
	}

	public void setPhoneEntity(PhoneEntity phone) {
		this.phone = phone;
	}

	public EmailEntity getEmailEntity() {
		return email;
	}

	public void setEmailEntity(EmailEntity email) {
		this.email = email;
	}
}
