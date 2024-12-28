package com.br.clean.arch.infra.gateways.address;

import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeEntity;

public class ChargeEntityMapper {

	public ChargeEntity toEntity(Charge charge) {
        if (charge == null) {
            return null;
        }
        return new ChargeEntity(
        		charge.getMain(),
        		charge.getReceiver(),
        		charge.getStreet(),
        		charge.getNumber(),
        		charge.getNeighborhood(),
        		charge.getCep(),
        		charge.getObservation(),
        		charge.getStreet(),
        		charge.getStreetType(),
        		charge.getTypeResidence(),
        		charge.getCity()
        );
    }

    public Charge toDomain(ChargeEntity chargeEntity) {
        if (chargeEntity == null) {
            return null;
        }
        return new Charge(
        		chargeEntity.getMain(),
        		chargeEntity.getReceiver(),
                chargeEntity.getStreet(),
                chargeEntity.getNumber(),
                chargeEntity.getNeighborhood(),
                chargeEntity.getCep(),
                chargeEntity.getObservation(),
                chargeEntity.getStreetType(),
                chargeEntity.getTypeResidence(),
                chargeEntity.getCity()
        );
    }
}
