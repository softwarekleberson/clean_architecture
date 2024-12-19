package com.br.clean.arch.infra.controller.delivery;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.address.delivery.ListDelivery;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	private final GetCustomer getCustomer;
	private final CreateDelivery createDelivery;
	private final ListDelivery listDelivery;
	
	public DeliveryController(GetCustomer getCustomer, CreateDelivery createDelivery, ListDelivery listDelivery) {
		this.getCustomer = getCustomer;
		this.createDelivery = createDelivery;
		this.listDelivery = listDelivery;
	}
	
	@PostMapping("/{cpf}")
	public DeliveryListDto createDelivery(@PathVariable String cpf, @RequestBody @Valid DeliveryDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		customer.addNewDelivery(new Delivery(dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
		Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
		return new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
	}
	
	@GetMapping("/{id}")
	public List<DeliveryListDto> listAllCustomers(@PathVariable String id){
		return listDelivery.listDelivery(id).
			   stream().
			   map(u -> new DeliveryListDto(u.getReceiver(), u.getStreet(), u.getNumber(), u.getNeighborhood(), u.getCep())).
			   collect(Collectors.toList());
	}
}
