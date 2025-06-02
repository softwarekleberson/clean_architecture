package com.br.clean.arch.infra.gateways.address;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class ChargeRepositoryJpa implements RepositoryCharge {

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
    public Charge save(String cpf, Charge charge) { 
        CustomerEntity customerEntity = customerRepository.findByCpf(cpf)
                                        .orElseThrow(() -> new CustomerNotFoundException("Customer not found for the given CPF: " + cpf));

        ChargeEntity entity = mapper.toEntity(charge);

        entity.setCustomerEntity(customerEntity); 
        ChargeEntity savedEntity = chargeRepository.save(entity);
        
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Charge updateCharge(Long id, Charge charge) {
        ChargeEntity existingEntity = findChargeById(id);

        mapper.updateEntityFromDomain(existingEntity, charge);
        ChargeEntity savedEntity = chargeRepository.save(existingEntity);

        return mapper.toDomain(savedEntity);
    }

    
    @Override
    public Page<Charge> listCharge(String customerId, Pageable pageable) {
        Page<ChargeEntity> chargeEntitiesPage = chargeRepository.findByCustomerId(customerId, pageable);
        return chargeEntitiesPage.map(mapper::toDomain);
    }

    @Override
    public void deleteCharge(Long id) {
        ChargeEntity entity = findChargeById(id);
        chargeRepository.delete(entity);
    }

    @Override
    public Charge ensuresAprimaryAddress(String cpf, boolean main) {
        CustomerEntity customer = findCustomerByCpf(cpf);

        if (main) {
            customer.getCharges().stream()
                    .filter(ChargeEntity::getMain)
                    .forEach(charge -> {
                        charge.setMain(false);
                        chargeRepository.save(charge);
                    });
        }

        return null;
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
    }

    private CustomerEntity findCustomerByCpf(String cpf) {
        return customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    private ChargeEntity findChargeById(Long id) {
        return chargeRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Delivery not found"));
    }

	@Override
	public Optional<Charge> findById(Long id) {
		return chargeRepository.findById(id).map(mapper::toDomain);
	}
}
