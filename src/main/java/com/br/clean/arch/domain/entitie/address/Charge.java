package com.br.clean.arch.domain.entitie.address;

public class Charge extends Address{

	public Charge(String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city) {
		super(receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city);
	}

	public Charge(String receiver, String street, String number, String neighborhood, String cep,
			String streetType, String typeResidence, String city) {
		super(receiver, street, number, neighborhood, cep, streetType, typeResidence, city);
	}

	@Override
	public String toString() {
		return "Charge []";
	}
	
}
