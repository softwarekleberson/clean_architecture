package com.br.clean.arch.infra.gateways.customer;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.CustomerUpdateDto;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

public class CustomerRepositoryJpa implements RepositoriyCustomer {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        entity.setPassword(encryptPassword(customer.getPassword()));
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public List<Customer> listCustomer() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(mapper::toDomain)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with CPF " + cpf + " not found"));
    }

    @Override
    public Customer updateCustomer(String id, CustomerUpdateDto dto) {
        CustomerEntity entity = repository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        updateIfNotNull(dto.phone().getDdd(), entity.getPhoneEntity()::setDdd);
        updateIfNotNull(dto.phone().getPhone(), entity.getPhoneEntity()::setPhone);
        updateIfNotNull(dto.birth(), entity::setBirth);
        updateIfNotNull(dto.name(), entity::setName);

        repository.save(entity);
        return mapper.toDomain(entity);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
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
