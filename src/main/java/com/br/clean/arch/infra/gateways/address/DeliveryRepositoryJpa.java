package com.br.clean.arch.infra.gateways.address;

import java.util.List;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class DeliveryRepositoryJpa implements RepositoryDelivery{

	private final CustomerRepository customerRepository;
	private final DeliveryRepository repository;
	private final DeliveryEntityMapper mapper;
	
	public DeliveryRepositoryJpa(CustomerRepository customerRepository, DeliveryRepository repository, DeliveryEntityMapper mapper) {
		this.repository = repository;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	@Override
	public Delivery createDelivery(String customerId, Delivery delivery) {	

	    CustomerEntity customerEntity = customerRepository.findByCpf(customerId);
		DeliveryEntity entity = mapper.toEntity(delivery);
		entity.setCustomerEntity(customerEntity);
		
		repository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public List<Delivery> listDelivery() {
		return null;
	}
}
