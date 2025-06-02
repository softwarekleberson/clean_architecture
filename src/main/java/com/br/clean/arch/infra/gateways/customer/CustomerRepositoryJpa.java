package com.br.clean.arch.infra.gateways.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class CustomerRepositoryJpa implements RepositoryCustomer {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    public CustomerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
        CustomerEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        mapper.updateEntityFromDomain(existingEntity, customer);

        CustomerEntity savedEntity = repository.save(existingEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Page<Customer> listCustomer(Pageable pageable) { // Added Pageable parameter
        Page<CustomerEntity> entityPage = repository.findAll(pageable); // Pass pageable to findAll()

        return entityPage.map(mapper::toDomain);
    }
    
    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}