package com.br.clean.arch.infra.persistence.address.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChargeRepository extends JpaRepository<ChargeEntity, Long>{
    
    @Query("SELECT c FROM ChargeEntity c WHERE c.customerEntity.id = :customerId")
    Page<ChargeEntity> findByCustomerId(@Param("customerId") String customerId, Pageable pageable);

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
