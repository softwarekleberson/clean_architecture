package com.br.clean.arch.domain.entitie.address;

public class Charge extends Address{

	public Charge(Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city, String state, String country) {
		super(main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city, state, country);
	}
	
	public Charge(Long id, Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city, String state, String country) {
		super(id, main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city, state, country);
	}

	@Override
	public String toString() {
		return "Charge []";
	}
}
