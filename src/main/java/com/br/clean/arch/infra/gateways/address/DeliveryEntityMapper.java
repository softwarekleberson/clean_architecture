package com.br.clean.arch.infra.gateways.address;

import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryEntity;

public class DeliveryEntityMapper {

	public DeliveryEntity toEntity(Delivery delivery) {
        if (delivery == null) {
            return null;
        }
        return new DeliveryEntity(
        		delivery.isMain(),
        		delivery.getReceiver(),
                delivery.getStreet(),
                delivery.getNumber(),
                delivery.getNeighborhood(),
                delivery.getCep(),
                delivery.getObservation(),
                delivery.getStreetType(),
                delivery.getTypeResidence(),
                delivery.getCity(),
                delivery.getDeliveryPhrase(),
                delivery.getState(),
                delivery.getCountry()
        );
    }

    public Delivery toDomain(DeliveryEntity deliveryEntity) {
        if (deliveryEntity == null) {
            return null;
        }
        return new Delivery(
        		deliveryEntity.getId(),
        		deliveryEntity.getMain(),
        		deliveryEntity.getReceiver(),
                deliveryEntity.getStreet(),
                deliveryEntity.getNumber(),
                deliveryEntity.getNeighborhood(),
                deliveryEntity.getCep(),
                deliveryEntity.getObservation(),
                deliveryEntity.getStreetType(),
                deliveryEntity.getTypeResidence(),
                deliveryEntity.getCity(),
                deliveryEntity.getDeliveryPhrase(),
                deliveryEntity.getState(),
                deliveryEntity.getCountry()
        		);
    }

    public void updateEntityFromDomain(DeliveryEntity entity, Delivery domainDelivery) {
        if (entity == null || domainDelivery == null) {
            return;
        }
        entity.setMain(domainDelivery.isMain());
        entity.setReceiver(domainDelivery.getReceiver());
        entity.setStreet(domainDelivery.getStreet());
        entity.setNumber(domainDelivery.getNumber());
        entity.setNeighborhood(domainDelivery.getNeighborhood());
        entity.setCep(domainDelivery.getCep());
        entity.setObservation(domainDelivery.getObservation());
        entity.setStreetType(domainDelivery.getStreetType());
        entity.setTypeResidence(domainDelivery.getTypeResidence());
        entity.setCity(domainDelivery.getCity());
        entity.setCity(domainDelivery.getState());
        entity.setCity(domainDelivery.getCountry());
    }
}
