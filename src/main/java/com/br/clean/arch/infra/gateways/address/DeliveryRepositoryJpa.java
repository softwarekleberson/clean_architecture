package com.br.clean.arch.infra.gateways.address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.delivery.DeliveryUpdateDto;
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
    public List<Delivery> listDelivery(String customerId) {
        return deliveryRepository.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Delivery updateDelivery(Long id, DeliveryUpdateDto dto) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Delivery not found"));

        updateDeliveryEntityFields(deliveryEntity, dto);

        deliveryRepository.save(deliveryEntity);
        return mapper.toDomain(deliveryEntity);
    }

    @Override
    public Delivery deleteDelivery(Long id) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Delivery not found"));

        deliveryRepository.delete(deliveryEntity);
        return mapper.toDomain(deliveryEntity);
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

    private void updateDeliveryEntityFields(DeliveryEntity entity, DeliveryUpdateDto dto) {
        Optional.ofNullable(dto.main()).ifPresent(entity::setMain);
        Optional.ofNullable(dto.receiver()).ifPresent(entity::setReceiver);
        Optional.ofNullable(dto.street()).ifPresent(entity::setStreet);
        Optional.ofNullable(dto.number()).ifPresent(entity::setNumber);
        Optional.ofNullable(dto.neighborhood()).ifPresent(entity::setNeighborhood);
        Optional.ofNullable(dto.cep()).ifPresent(entity::setCep);
        Optional.ofNullable(dto.observation()).ifPresent(entity::setObservation);
        Optional.ofNullable(dto.streetType()).ifPresent(entity::setStreetType);
        Optional.ofNullable(dto.typeResidence()).ifPresent(entity::setTypeResidence);
        Optional.ofNullable(dto.city()).ifPresent(entity::setCity);
        Optional.ofNullable(dto.deliveryPhrase()).ifPresent(entity::setDeliveryPhrase);
    }

	@Override
	public Optional<Delivery> findById(Long id) {
		return deliveryRepository.findById(id).map(mapper::toDomain);
	}
}
