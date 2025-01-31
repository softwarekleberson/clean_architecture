package com.br.clean.arch.domain.entitie.address;

public class Charge extends Address{

	public Charge(Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city) {
		super(main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city);
	}
	
	public Charge(Long id, Boolean main, String receiver, String street, String number, String neighborhood, String cep, String observation,
			String streetType, String typeResidence, String city) {
		super(id, main, receiver, street, number, neighborhood, cep, observation, streetType, typeResidence, city);
	}

	@Override
	public String toString() {
		return "Charge []";
	}
}
