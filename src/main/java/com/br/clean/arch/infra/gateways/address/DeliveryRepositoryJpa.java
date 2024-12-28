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
	private final DeliveryRepository deliveryRepository;
	private final DeliveryEntityMapper mapper;
	
	public DeliveryRepositoryJpa(CustomerRepository customerRepository, DeliveryRepository deliveryRepository, DeliveryEntityMapper mapper) {
		this.deliveryRepository = deliveryRepository;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	@Override
	public Delivery createDelivery(String customerId, Delivery delivery) {	
	    Optional<CustomerEntity> optDataBase = customerRepository.findByCpf(customerId);
		if(optDataBase.isEmpty()) {
			throw new IllegalArgumentException("Customer not found");
		}
		
	    CustomerEntity customer = optDataBase.get();
	    DeliveryEntity entity = mapper.toEntity(delivery);
		entity.setCustomerEntity(customer);
		
		deliveryRepository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public List<Delivery> listDelivery(String customerId) {
		return deliveryRepository.findByCustomerId(customerId).stream()
			   .map(mapper::toDomain)
			   .collect(Collectors.toList());

	}

	@Override
	public Delivery updateDelivery(Long id, DeliveryUpdateDto dto) {
		Optional<DeliveryEntity> opDataBaseDelivery = deliveryRepository.findById(id);
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
			
			deliveryRepository.save(entity);
			return mapper.toDomain(entity);
		}
		
		else {
			throw new IllegalArgumentException("Delivery not found");
		}
	}

	@Override
	public Delivery deleteDelivery(Long id) {
		Optional<DeliveryEntity> opDataBaseDelivery = deliveryRepository.findById(id);
		if(opDataBaseDelivery.isPresent()) {
			DeliveryEntity entity = opDataBaseDelivery.get();
			deliveryRepository.delete(entity);
		}
		return null;
	}

	@Override
	public Delivery ensuresAprimaryAddress(String cpf, boolean main) {
		Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
	    if(customerEntity.isEmpty()) {
	    	throw new IllegalArgumentException("Customer not found");
	    }
	    
	    if(main) {
	    	List<DeliveryEntity> entity = customerEntity.get().getDelivery();
	 	    for(DeliveryEntity verify : entity) {
	 	    	if(verify.getMain()) {
	 	    	   verify.setMain(false);
	 	    	   deliveryRepository.save(verify);
	 	    	}
	 	    }
	    }
		return null;	   
	}

	@Override
	public Delivery customerIsActive(String id) {
		
		if(id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Id of customer not present");
		}
		
		Optional<CustomerEntity> obtDataBase = customerRepository.findById(id);
		if(obtDataBase.isEmpty()) {
			throw new IllegalArgumentException("Customer not found");
		}
		
		CustomerEntity customer = obtDataBase.get();

		boolean customerMostByActive = deliveryRepository.existsDeliveryAndChargeByCustomerId(id);	
		if(customerMostByActive) {
			customer.setActive(true);
			customerRepository.save(customer);
		}
		return null;
	}
}
