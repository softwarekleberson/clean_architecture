package com.br.clean.arch.domain.entitie.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.card.Card;
import com.br.clean.arch.domain.entitie.customer.exceptions.IllegalCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.UnderageException;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;
import com.br.clean.arch.domain.entitie.valueObject.Role;

public class Customer {

	private String id;
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	
	public static final int LENGHT_PASSWORD = 8;
	private String password;
	
	private Gender gender;
	private Phone phone;
	private Email email;
	
	private Role role = Role.ROLE_CUSTOMER;
	
	private List<Delivery> deliveries = new ArrayList<Delivery>();
	private List<Charge> charges = new ArrayList<Charge>();
	private List<Card> cards = new ArrayList<>();
	
	public Customer(String cpf, String name,
					 LocalDate birth, String password,
					 Gender gender, Phone phone,
					 Email email) {
		
		setCpf(cpf);
		setBirth(birth);		
		checkCharacterQuantity(password);
		
		this.active = false;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}
	
	public Customer(String cpf, String name,
			 LocalDate birth, Gender gender,
			 Phone phone, Email email) {

		setCpf(cpf);
		setBirth(birth);

		this.active = false;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}

	public Customer(String id, boolean active ,String cpf, String name, LocalDate birth, Gender gender, Phone phone, Email email, String password) {
		super();
		this.id = id;
		this.active = active;
		this.cpf = cpf;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public void addNewDelivery(Delivery newDelivery) {
		validateDelivery(newDelivery);
		handleMainDeliveryStatus(newDelivery);
		existingDeliveryCheck(newDelivery);
		
		
		System.out.println("Bati aqui em");
		this.deliveries.add(newDelivery);
	}

	public void addNewCharge(Charge newCharge) {
		validateCharge(newCharge);
		handleMainChargeStatus(newCharge);
		existingChargeCheck(newCharge);
		
		this.charges.add(newCharge);
	}
	
	public void addNewCard(Card newCard) {
		validateCard(newCard);
		handleMainCardStatus(newCard);
		
		this.cards.add(newCard);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Charge> getCharges() {
		return Collections.unmodifiableList(this.charges);
	}
	
	public List<Delivery> getDeliveries() {
		return Collections.unmodifiableList(this.deliveries);
	}
	
	public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
	    String regexCpf = "^\\d{11}$";
		if(cpf == null || !cpf.matches(regexCpf)) {
			throw new IllegalCpfException("Cpf does not match the required format");
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
			throw new UnderageException("For register you need 18 years");
		}
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Role getRole() {
		return role;
	}

	public void updateDetails(String newName, LocalDate newBirth, String newPhoneDdd , String newPhoneNumber ) {
		if(newName != null) {
			this.name = newName;
		}
		
		if(newBirth != null) {
			this.birth = newBirth;
		}
		
		if (newPhoneDdd != null || newPhoneNumber != null) {
	           String currentDdd = (this.phone != null) ? this.phone.getDdd() : null;
	           String currentPhoneNum = (this.phone != null) ? this.phone.getPhone() : null;

	           String finalDdd = (newPhoneDdd != null) ? newPhoneDdd : currentDdd;
	           String finalPhoneNum = (newPhoneNumber != null) ? newPhoneNumber : currentPhoneNum;

	           if (finalDdd != null || finalPhoneNum != null) {
	               this.phone = new Phone(finalDdd, finalPhoneNum);
	           }
	     }
	}

	private void handleMainDeliveryStatus(Delivery newDelivery) {
		if(newDelivery.isMain()) {
			this.deliveries.forEach(delivery -> delivery.setMain(false));
		}
	}

	private void validateDelivery(Delivery newDelivery) {
		if(newDelivery == null) {
            throw new IllegalArgumentException("Delivery cannot be null.");
		}
	}
	
	private void handleMainChargeStatus(Charge newCharge) {
		if(newCharge.isMain()) {
			this.charges.forEach(charge -> charge.setMain(false));
		}
	}

	private void validateCharge(Charge newCharge) {
		if(newCharge == null) {
            throw new IllegalArgumentException("Charge cannot be null.");
		}
	}
	
	private void validateCard(Card newCard) {
		if(newCard == null) {
            throw new IllegalArgumentException("Card cannot be null.");
		}
	}
	
	private void checkCharacterQuantity(String password) {
		if(password == null || password.length() < LENGHT_PASSWORD) {
	        throw new IllegalArgumentException("The password requires at least 8 characters to be valid");
		}
	}

	private void handleMainCardStatus(Card newCard) {
		if(newCard.isMain()) {
			this.cards.forEach(card -> card.setMain(false));
		}
	}
	
	private void existingDeliveryCheck(Delivery newDelivery) {
		if(this.deliveries.stream().anyMatch(c -> c.equals(newDelivery))) {
	        throw new IllegalArgumentException("Delivery with the same ID already exists.");
		}
	}

	private void existingChargeCheck(Charge newCharge) {
		if(this.charges.stream().anyMatch(c -> c.equals(newCharge))) {
	        throw new IllegalArgumentException("Charge with the same ID already exists.");
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cpf=" + cpf + ", active=" + active + ", name=" + name + ", birth=" + birth
				+ ", password=" + password + ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", role="
				+ role + ", deliveries=" + deliveries + ", charges=" + charges + ", cards=" + cards + "]";
	}
}
