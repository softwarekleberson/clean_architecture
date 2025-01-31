package com.br.clean.arch.infra.gateways.address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.charge.ChargeUpdateDto;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeRepository;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;
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
    public List<Charge> listCharge(String customerId) {
        return chargeRepository.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Charge createCharge(String cpf, Charge charge) {
        CustomerEntity customer = findCustomerByCpf(cpf);
        ChargeEntity entity = mapper.toEntity(charge);
        entity.setCustomerEntity(customer);
        
        ChargeEntity savedEntity = chargeRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Charge updateCharge(Long id, ChargeUpdateDto dto) {
        ChargeEntity entity = findChargeById(id);

        updateChargeEntityFields(entity, dto);
        chargeRepository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Charge deleteCharge(Long id) {
        ChargeEntity entity = findChargeById(id);
        chargeRepository.delete(entity);
        return null;
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
    public Charge customerIsActive(String id) {
        if (id == null || id.isEmpty()) {
            throw new CustomerNotFoundException("Id of customer not present");
        }

        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if (chargeRepository.existsDeliveryAndChargeByCustomerId(id)) {
            customer.setActive(true);
            customerRepository.save(customer);
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

    private void updateChargeEntityFields(ChargeEntity entity, ChargeUpdateDto dto) {
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
    }

	@Override
	public Optional<Charge> findById(Long id) {
		return chargeRepository.findById(id).map(mapper::toDomain);
	}
}
