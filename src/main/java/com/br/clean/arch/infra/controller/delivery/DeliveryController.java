package com.br.clean.arch.infra.controller.delivery;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<DeliveryListDto> createDelivery(@PathVariable String cpf, @RequestBody @Valid DeliveryDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		ensuresAprimaryAddress.ensuresAprimaryAddress(cpf, dto.main());
		customer.addNewDelivery(new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));

		if(dto.observation() != null) {
			Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.getId());
			DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
			return ResponseEntity.created(URI.create("/delivery/" + delivery.getCep()))
					   					 .body(deliveryListDto);
		}
		else {	
			Delivery delivery = createDelivery.createDelivery(cpf, new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.getId());
			DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
			return ResponseEntity.created(URI.create("/delivery/" + delivery.getCep()))
  					 .body(deliveryListDto);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<DeliveryListDto>> listAllDeliveries(
	        @PathVariable String id, 
	        @PageableDefault(size = 10) Pageable pageable) {
	    
	    List<Delivery> allDeliveries = listDelivery.listDelivery(id);

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allDeliveries.size());

	    if (start >= allDeliveries.size()) {
	        return ResponseEntity.noContent().build();
	    }

	    List<DeliveryListDto> paginatedDeliveries = allDeliveries.subList(start, end)
	            .stream()
	            .map(u -> new DeliveryListDto(u.getReceiver(), u.getStreet(), u.getNumber(), u.getNeighborhood(), u.getCep()))
	            .toList();

	    Page<DeliveryListDto> page = new PageImpl<>(paginatedDeliveries, pageable, allDeliveries.size());

	    return ResponseEntity.ok(page);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<DeliveryListDto> updateDelivery(@PathVariable Long id, @RequestBody @Valid DeliveryUpdateDto dto) {
		Delivery delivery = upadateDelivery.updateDelivery(id, dto);
		DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep());
		return ResponseEntity.ok(deliveryListDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
		try {
			deleteDelivery.deleteDelivery(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
