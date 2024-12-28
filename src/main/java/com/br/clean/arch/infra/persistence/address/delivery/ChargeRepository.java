package com.br.clean.arch.infra.persistence.address.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChargeRepository extends JpaRepository<ChargeEntity, Long>{
    
    @Query("SELECT c FROM ChargeEntity c WHERE c.customerEntity.id = :customerId")
    List<ChargeEntity> findByCustomerId(@Param("customerId") String customerId);

    @Query("""
            SELECT CASE 
                WHEN COUNT(DISTINCT d) > 0 AND COUNT(DISTINCT c) > 0 THEN true
                ELSE false 
            END
            FROM CustomerEntity cu
            LEFT JOIN DeliveryEntity d ON d.customerEntity.id = cu.id
            LEFT JOIN ChargeEntity c ON c.customerEntity.id = cu.id
            WHERE cu.id = :customerId
          """)
    boolean existsDeliveryAndChargeByCustomerId(String customerId);
}
