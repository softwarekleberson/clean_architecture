package com.br.clean.arch.infra.persistence.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{

	Optional<CustomerEntity> findByCpf(String cpf);

}
