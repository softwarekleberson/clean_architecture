package com.br.clean.arch.infra.gateways.address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.controller.delivery.DeliveryUpdateDto;
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
	public List<Delivery> listDelivery(String customerId) {
		return repository.findByCustomerId(customerId).stream()
			   .map(mapper::toDomain)
			   .collect(Collectors.toList());

	}

	@Override
	public Delivery updateDelivery(Long id, DeliveryUpdateDto dto) {
		Optional<DeliveryEntity> opDataBaseDelivery = repository.findById(id);
		if(opDataBaseDelivery.isPresent()) {
			DeliveryEntity entity = opDataBaseDelivery.get();
			
			if(dto.main() != null) {
				entity.setMain(dto.main());
			}
			
			if(dto.receiver() != null) {
				entity.setReceiver(dto.receiver());
			}
			
			if(dto.street() != null) {
				entity.setStreet(dto.street());
			}
			
			if(dto.number() != null) {
				entity.setNumber(dto.number());
			}
			
			if(dto.neighborhood() != null) {
				entity.setNeighborhood(dto.neighborhood());
			}
			
			if(dto.cep() != null) {
				entity.setCep(dto.cep());
			}
			
			if(dto.observation() != null) {
				entity.setObservation(dto.observation());
			}
			
			if(dto.streetType() != null) {
				entity.setStreetType(dto.streetType());
			}
			
			if(dto.typeResidence() != null) {
				entity.setTypeResidence(dto.typeResidence());
			}
			
			if(dto.city() != null) {
				entity.setCity(dto.city());
			}
			
			if(dto.deliveryPhrase() != null) {
				entity.setDeliveryPhrase(dto.deliveryPhrase());
			}
			
			repository.save(entity);
			return mapper.toDomain(entity);
		}
		
		else {
			throw new IllegalArgumentException("Delivery not found");
		}
	}

	@Override
	public Delivery deleteDelivery(Long id) {
		Optional<DeliveryEntity> opDataBaseDelivery = repository.findById(id);
		if(opDataBaseDelivery.isPresent()) {
			DeliveryEntity entity = opDataBaseDelivery.get();
			repository.delete(entity);
		}
		return null;
	}

	@Override
	public Delivery verifyMainDelivery(String cpf) {
	    CustomerEntity customerEntity = customerRepository.findByCpf(cpf);
	    if(customerEntity == null) {
	    	throw new IllegalArgumentException("Customer not found");
	    }
	    
	    List<DeliveryEntity> entity = customerEntity.getDelivery();
	    for(DeliveryEntity verify : entity) {
	    	if(verify.getMain()) {
	    		customerEntity.setActive(true);
	    		customerRepository.save(customerEntity);
	    		break;
	    	}
	    }
		return null;
	}

	@Override
	public Delivery ensuresAprimaryAddress(String cpf, boolean principal) {
		CustomerEntity customerEntity = customerRepository.findByCpf(cpf);
	    if(customerEntity == null) {
	    	throw new IllegalArgumentException("Customer not found");
	    }
	    
	    if(principal) {
	    	List<DeliveryEntity> entity = customerEntity.getDelivery();
	 	    for(DeliveryEntity verify : entity) {
	 	    	if(verify.getMain()) {
	 	    	   verify.setMain(false);
	 	    	   repository.save(verify);
	 	    	}
	 	    }
	    }
		return null;	   
	}
}
