package com.br.clean.arch.infra.gateways.address;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;

public class ChargeEntityMapper {

	public ChargeEntity toEntity(Charge charge) {
        if (charge == null) {
            return null;
        }
        return new ChargeEntity(
        		charge.getId(),
        		charge.isMain(),
        		charge.getReceiver(),
        		charge.getStreet(),
        		charge.getNumber(),
        		charge.getNeighborhood(),
        		charge.getCep(),
        		charge.getObservation(),
        		charge.getStreet(),
        		charge.getStreetType(),
        		charge.getTypeResidence(),
        		charge.getCity(),
        		charge.getState(),
        		charge.getCountry()
        );
    }

    public Charge toDomain(ChargeEntity chargeEntity) {
        if (chargeEntity == null) {
            return null;
        }
        return new Charge(
        		chargeEntity.getId(),
        		chargeEntity.getMain(),
        		chargeEntity.getReceiver(),
                chargeEntity.getStreet(),
                chargeEntity.getNumber(),
                chargeEntity.getNeighborhood(),
                chargeEntity.getCep(),
                chargeEntity.getObservation(),
                chargeEntity.getStreetType(),
                chargeEntity.getTypeResidence(),
                chargeEntity.getCity(),
                chargeEntity.getState(),
                chargeEntity.getState()
        );
    }
    
   
   public void updateEntityFromDomain(ChargeEntity entity, Charge domainCharge) {
       if (entity == null || domainCharge == null) {
           return;
       }
       entity.setMain(domainCharge.isMain());
       entity.setReceiver(domainCharge.getReceiver());
       entity.setStreet(domainCharge.getStreet());
       entity.setNumber(domainCharge.getNumber());
       entity.setNeighborhood(domainCharge.getNeighborhood());
       entity.setCep(domainCharge.getCep());
       entity.setObservation(domainCharge.getObservation());
       entity.setStreetType(domainCharge.getStreetType());
       entity.setTypeResidence(domainCharge.getTypeResidence());
       entity.setCity(domainCharge.getCity());
       entity.setCity(domainCharge.getState());
       entity.setCity(domainCharge.getCountry());
   }
}

