package com.br.clean.arch.domain.entitie.address;

public abstract class Address {
	
	protected Long id;
	protected Boolean main;
	protected String receiver;
	protected String street;
	protected String number;
	protected String neighborhood;
	
	protected String cep;
	protected String observation;
	protected String streetType;
	protected String typeResidence;
	protected String city;
	
	public Address(Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city) {
		
		setCep(cep);

		this.main = main;
		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.observation = observation;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
	}

	public Address(Boolean main, String receiver, String street, String number, String neighborhood, String cep, String streetType,
			String typeResidence, String city) {

		setCep(cep);
		
		this.main = main;
		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getMain() {
		return main;
	}
	
	public void setMain(Boolean main) {
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
			throw new IllegalArgumentException("Format cep incorrect, you need respect this format xxxxx-xxx");
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

	@Override
	public String toString() {
		return "Address [receiver=" + receiver + ", street=" + street + ", number=" + number + ", neighborhood="
				+ neighborhood + ", cep=" + cep + ", observation=" + observation + ", streetType=" + streetType
				+ ", typeResidence=" + typeResidence + ", city=" + city + "]";
	}
}
