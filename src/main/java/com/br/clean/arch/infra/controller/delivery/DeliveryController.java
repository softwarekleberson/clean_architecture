package com.br.clean.arch.infra.controller.delivery;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.address.delivery.CustomerIsActiveDelivery;
import com.br.clean.arch.application.usecases.address.delivery.DeleteDelivery;
import com.br.clean.arch.application.usecases.address.delivery.EnsuresAprimaryDelivery;
import com.br.clean.arch.application.usecases.address.delivery.ListDelivery;
import com.br.clean.arch.application.usecases.address.delivery.UpdateDelivery;
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
	private final UpdateDelivery upadateDelivery;
	private final DeleteDelivery deleteDelivery;
	private final EnsuresAprimaryDelivery ensuresAprimaryAddress;
	private final CustomerIsActiveDelivery customerIsActiveDelivery;
	
	public DeliveryController(GetCustomer getCustomer, CreateDelivery createDelivery, ListDelivery listDelivery, UpdateDelivery upadateDelivery, DeleteDelivery deleteDelivery, EnsuresAprimaryDelivery ensuresAprimaryAddress, CustomerIsActiveDelivery customerIsActiveDelivery) {
		this.getCustomer = getCustomer;
		this.createDelivery = createDelivery;
		this.listDelivery = listDelivery;
		this.upadateDelivery = upadateDelivery;
		this.deleteDelivery = deleteDelivery;
		this.ensuresAprimaryAddress = ensuresAprimaryAddress;
		this.customerIsActiveDelivery = customerIsActiveDelivery;
	}
	
	@PostMapping("/{cpf}")
	public DeliveryListDto createDelivery(@PathVariable String cpf, @RequestBody @Valid DeliveryDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		ensuresAprimaryAddress.ensuresAprimaryAddress(cpf, dto.main());
		customer.addNewDelivery(new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));

		if(dto.observation() != null) {
			Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.getId());
			return new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
		}
		else {	
			Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.getId());
			return new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
		}
	}
	
	@GetMapping("/{id}")
	public List<DeliveryListDto> listAllCustomers(@PathVariable String id){
		return listDelivery.listDelivery(id).
			   stream().
			   map(u -> new DeliveryListDto(u.getReceiver(), u.getStreet(), u.getNumber(), u.getNeighborhood(), u.getCep())).
			   collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public DeliveryListDto updateDelivery(@PathVariable Long id, @RequestBody @Valid DeliveryUpdateDto dto) {
		Delivery delivery = upadateDelivery.updateDelivery(id, dto);
		return new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
	}
	
	@DeleteMapping("/{id}")
	public void deleteDelivery(@PathVariable Long id) {
		deleteDelivery.deleteDelivery(id);
	}
}
