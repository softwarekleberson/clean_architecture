package com.br.clean.arch.infra.persistence.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepositoy extends JpaRepository<CardEntity, Long>{

}
