package com.br.clean.arch.infra.gateways.address;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class DeliveryRepositoryJpa implements RepositoryDelivery {

    private final CustomerRepository customerRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryEntityMapper mapper;
    private final CustomerEntityMapper customerEntityMapper;

    public DeliveryRepositoryJpa(CustomerRepository customerRepository, 
                                 DeliveryRepository deliveryRepository, 
                                 DeliveryEntityMapper mapper, 
                                 CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.deliveryRepository = deliveryRepository;
        this.mapper = mapper;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public Delivery createDelivery(String customerId, Delivery delivery) {
        CustomerEntity customerEntity = customerRepository.findByCpf(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        DeliveryEntity entity = mapper.toEntity(delivery);
        entity.setCustomerEntity(customerEntity);

        DeliveryEntity savedEntity = deliveryRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Page<Delivery> listDelivery(String customerId, Pageable pageable) {
        return deliveryRepository.findByCustomerId(customerId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Delivery updateDelivery(Long id, Delivery delivery) {
        DeliveryEntity existingEntity = findDeliveryById(id);

        mapper.updateEntityFromDomain(existingEntity, delivery);
        DeliveryEntity savedEntity = deliveryRepository.save(existingEntity);

        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteDelivery(Long id) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Delivery not found"));

        deliveryRepository.delete(deliveryEntity);
        mapper.toDomain(deliveryEntity);
    }

    @Override
    public Delivery ensuresAprimaryAddress(String cpf, boolean main) {
        CustomerEntity customerEntity = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if (main) {
            customerEntity.getDelivery().stream()
                    .filter(DeliveryEntity::getMain)
                    .forEach(existingDelivery -> {
                        existingDelivery.setMain(false);
                        deliveryRepository.save(existingDelivery);
                    });
        }
        return null;
    }

    @Override
    public Delivery customerIsActive(String id) {
        if (id == null || id.isBlank()) {
            throw new CustomerNotFoundException("Customer ID is required");
        }

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if (deliveryRepository.existsDeliveryAndChargeByCustomerId(id)) {
            customerEntity.setActive(true);
            customerRepository.save(customerEntity);
        }
        return null;
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).map(customerEntityMapper::toDomain);
    }

    private DeliveryEntity findDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Delivery not found"));
    }

    @Override
	public Optional<Delivery> findById(Long id) {
		return deliveryRepository.findById(id).map(mapper::toDomain);
	}
}
