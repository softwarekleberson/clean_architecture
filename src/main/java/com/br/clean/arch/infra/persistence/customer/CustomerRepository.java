package com.br.clean.arch.infra.persistence.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{

	Optional<CustomerEntity> findByCpf(String cpf);

	@Query("SELECT c FROM CustomerEntity c WHERE c.email.email = :email")
	Optional<CustomerEntity> findByEmail(@Param("email") String email);

}
