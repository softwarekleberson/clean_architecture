package com.br.clean.arch.infra.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{

	CustomerEntity findByCpf(String cpf);

}
