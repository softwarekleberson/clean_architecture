package com.br.clean.arch.infra.persistence.card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepositoy extends JpaRepository<CardEntity, Long>{

	@Query("SELECT d FROM CardEntity d WHERE d.customerEntity.id = :customerId")
    Page<CardEntity> findByCustomerId(@Param("customerId") String customerId, Pageable pageable);

	boolean existsByNumberCard(String numberCard);
}
