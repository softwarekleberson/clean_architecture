package com.br.clean.arch.infra.controller.delivery;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	private final GetCustomer getCustomer;
	private final CreateDelivery createDelivery;
	
	public DeliveryController(GetCustomer getCustomer, CreateDelivery createDelivery) {
		this.getCustomer = getCustomer;
		this.createDelivery = createDelivery;
	}
	
	@PostMapping("/{cpf}")
	public DeliveryListDto createDelivery(@PathVariable String cpf, @RequestBody @Valid DeliveryDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		customer.addNewDelivery(new Delivery(dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
		Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
		return new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
	}
}
