package com.br.clean.arch.infra.persistence.address.delivery;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long>{
    
    @Query("SELECT d FROM DeliveryEntity d WHERE d.customerEntity.id = :customerId")
    List<DeliveryEntity> findByCustomerId(@Param("customerId") String customerId);
}
