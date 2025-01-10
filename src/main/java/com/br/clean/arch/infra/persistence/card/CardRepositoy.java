package com.br.clean.arch.infra.persistence.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepositoy extends JpaRepository<CardEntity, Long>{

	@Query("SELECT d FROM CardEntity d WHERE d.customerEntity.id = :customerId")
    List<CardEntity> findByCustomerId(@Param("customerId") String customerId);

	boolean existsByNumberCard(String numberCard);
}
