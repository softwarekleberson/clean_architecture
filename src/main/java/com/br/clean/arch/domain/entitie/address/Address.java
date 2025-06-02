package com.br.clean.arch.domain.entitie.address;

import java.util.Objects;

import com.br.clean.arch.domain.entitie.address.exception.IncorrectCepException;

public abstract class Address {
	
	protected Long id;
	protected boolean main;
	protected String receiver;
	protected String street;
	protected String number;
	protected String neighborhood;
	
	protected String cep;
	protected String observation;
	protected String streetType;
	protected String typeResidence;
	protected String city;
	protected String state;
	protected String country;
	
	public Address(Long id, boolean main, String receiver, String street, String number, String neighborhood,
			String cep, String observation, String streetType, String typeResidence, String city, String state,
			String country) {
		super();
		this.id = id;
		this.main = main;
		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.cep = cep;
		this.observation = observation;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Address(boolean main, String receiver, String street, String number, String neighborhood, String cep,
			String observation, String streetType, String typeResidence, String city, String state, String country) {
		super();
		this.main = main;
		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.cep = cep;
		this.observation = observation;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMain() {
		return main;
	}
	
	public void setMain(boolean main) {
		this.main = main;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		String regexCep = "^\\d{5}-\\d{3}$";
		if(cep == null || !cep.matches(regexCep)) {
			throw new IncorrectCepException("Format cep incorrect, you need respect this format xxxxx-xxx");
		}
		this.cep = cep;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getTypeResidence() {
		return typeResidence;
	}

	public void setTypeResidence(String typeResidence) {
		this.typeResidence = typeResidence;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void updateDetails(Boolean newMain, String newReceiver, String newStreet, String newNumber, String newNeighborhood,
			String newCep, String newObservation, String newStreetType, String newTypeResidence, String newCity, String newState, String newCountry) {
		
		if(newMain != null) {
			this.main = newMain;
		}
		
		if(newReceiver != null) {
			this.receiver = newReceiver;
		}
		
		if(newStreet != null) {
			this.street = newStreet;
		}
		
		if(newNumber != null) {
			this.number = newNumber;
		}
		
		if(newNeighborhood != null) {
			this.neighborhood = newNeighborhood;
		}
		
		if(newCep != null) {
			this.cep = newCep;
		}
		
		if(newObservation != null) {
			this.observation = newObservation;
		}
		
		if(newStreetType != null) {
			this.streetType = newStreetType;
		}
		
		if(newTypeResidence != null) {
			this.typeResidence = newTypeResidence;
		}
		
		if(newCity != null) {
			this.city = newCity;
		}
		
		if (newState != null) {
			this.city = newState;
		}
		
		if(newCountry != null) {
			this.country = newCountry;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, city, country, neighborhood, number, observation, receiver, state, street, streetType,
				typeResidence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(number, other.number) && Objects.equals(observation, other.observation)
				&& Objects.equals(receiver, other.receiver) && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street) && Objects.equals(streetType, other.streetType)
				&& Objects.equals(typeResidence, other.typeResidence);
	}

	@Override
	public String toString() {
		return "Address [receiver=" + receiver + ", street=" + street + ", number=" + number + ", neighborhood="
				+ neighborhood + ", cep=" + cep + ", observation=" + observation + ", streetType=" + streetType
				+ ", typeResidence=" + typeResidence + ", city=" + city + "]";
	}
}
