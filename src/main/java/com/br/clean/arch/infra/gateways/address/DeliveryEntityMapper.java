package com.br.clean.arch.infra.gateways.address;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;

public class DeliveryEntityMapper {

	public DeliveryEntity toEntity(Delivery delivery) {
        if (delivery == null) {
            return null;
        }
        return new DeliveryEntity(
        		delivery.getReceiver(),
                delivery.getStreet(),
                delivery.getNumber(),
                delivery.getNeighborhood(),
                delivery.getCep(),
                delivery.getObservation(),
                delivery.getStreetType(),
                delivery.getTypeResidence(),
                delivery.getCity(),
                delivery.getDeliveryPhrase()
        );
    }

    public Delivery toDomain(DeliveryEntity deliveryEntity) {
        if (deliveryEntity == null) {
            return null;
        }
        return new Delivery(
        		deliveryEntity.getReceiver(),
                deliveryEntity.getStreet(),
                deliveryEntity.getNumber(),
                deliveryEntity.getNeighborhood(),
                deliveryEntity.getCep(),
                deliveryEntity.getObservation(),
                deliveryEntity.getStreetType(),
                deliveryEntity.getTypeResidence(),
                deliveryEntity.getCity(),
                deliveryEntity.getDeliveryPhrase()
        );
    }
}
