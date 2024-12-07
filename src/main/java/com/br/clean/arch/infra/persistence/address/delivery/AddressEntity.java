package com.br.clean.arch.infra.persistence.address.delivery;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String receiver;
	protected String street;
	protected String number;
	protected String neighborhood;
	
	protected String cep;
	protected String observation;
	protected String streetType;
	protected String typeResidence;
	protected String city;
}
