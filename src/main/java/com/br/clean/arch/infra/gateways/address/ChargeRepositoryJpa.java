package com.br.clean.arch.infra.gateways.address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class ChargeRepositoryJpa implements RepositoryCharge{

	private final CustomerRepository customerRepository;
	private final ChargeRepository chargeRepository;
	private final ChargeEntityMapper mapper;
	private final CustomerEntityMapper customerEntityMapper;
	
	public ChargeRepositoryJpa(CustomerRepository customerRepository, ChargeRepository repository, ChargeEntityMapper mapper, CustomerEntityMapper customerEntityMapper) {
		this.chargeRepository = repository;
		this.customerRepository = customerRepository;
		this.mapper = mapper;
		this.customerEntityMapper = customerEntityMapper;
	}

	@Override
	public List<Charge> listCharge(String customerId) {
		return chargeRepository.findByCustomerId(customerId).stream()
			   .map(mapper::toDomain)
			   .collect(Collectors.toList());
	}

	@Override
	public Charge createCharge(String cpf, Charge charge) {
		
		Optional<CustomerEntity> optDataBase = customerRepository.findByCpf(cpf);
		if(optDataBase.isEmpty()) {
			throw new IllegalArgumentException("Customer not found");
		}
		
		CustomerEntity customer = optDataBase.get();
		ChargeEntity entity = mapper.toEntity(charge);
		entity.setCustomerEntity(customer);
		
		chargeRepository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public Charge updateCharge(Long id, ChargeUpdateDto dto) {
		
		Optional<ChargeEntity> opDataBaseCharge = chargeRepository.findById(id);
		if(opDataBaseCharge.isPresent()) {
			ChargeEntity entity = opDataBaseCharge.get();
			
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
						
			chargeRepository.save(entity);
			return mapper.toDomain(entity);
		}
		
		else {
			throw new IllegalArgumentException("Delivery not found");
		}
	}

	@Override
	public Charge deleteCharge(Long id) {
		
		Optional<ChargeEntity> opDataBaseCharge = chargeRepository.findById(id);
		if(opDataBaseCharge.isPresent()) {
			ChargeEntity entity = opDataBaseCharge.get();
			chargeRepository.delete(entity);
		}
		return null;
	}

	@Override
	public Charge ensuresAprimaryAddress(String cpf, boolean main) {
		Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
	    if(customerEntity == null) {
	    	throw new IllegalArgumentException("Customer not found");
	    }
	    
	    if(main) {
	    	List<ChargeEntity> entities = customerEntity.get().getCharges();
	    	for(ChargeEntity verify : entities) {
	    		if(verify.getMain()) {
	    			verify.setMain(false);
	    			chargeRepository.save(verify);
	    		}
	    	}
	    }
		return null;
	}

	
	@Override
	public Charge customerIsActive(String id) {
		
		if(id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Id of customer not present");
		}
		
		Optional<CustomerEntity> obtDataBase = customerRepository.findById(id);
		if(obtDataBase.isEmpty()) {
			throw new IllegalArgumentException("Customer not found");
		}
		
		CustomerEntity customer = obtDataBase.get();

		boolean customerMostByActive = chargeRepository.existsDeliveryAndChargeByCustomerId(id);	
		if(customerMostByActive) {
			customer.setActive(true);
			customerRepository.save(customer);
		}
		return null;
	}

	@Override
	public Optional<Customer> findByCpf(String cpf) {
		return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
	}
}
