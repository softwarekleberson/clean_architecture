package com.br.clean.arch.infra.controller.delivery;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.br.clean.arch.application.usecases.customer.GetCustomerById;
import com.br.clean.arch.domain.entitie.address.Delivery;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.delivery.input.DeliveryDto;
import com.br.clean.arch.infra.controller.delivery.input.DeliveryUpdateDto;
import com.br.clean.arch.infra.controller.delivery.output.DeliveryListDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/delivery")
@SecurityRequirement(name = "bearer-key")
public class DeliveryController {

	private final GetCustomerById getCustomerById;
	private final CreateDelivery createDelivery;
	private final ListDelivery listDelivery;
	private final UpdateDelivery upadateDelivery;
	private final DeleteDelivery deleteDelivery;
	private final EnsuresAprimaryDelivery ensuresAprimaryAddress;
	private final CustomerIsActiveDelivery customerIsActiveDelivery;
	
	public DeliveryController(GetCustomerById getCustomerById, CreateDelivery createDelivery, ListDelivery listDelivery, UpdateDelivery upadateDelivery, DeleteDelivery deleteDelivery, EnsuresAprimaryDelivery ensuresAprimaryAddress, CustomerIsActiveDelivery customerIsActiveDelivery) {
		this.getCustomerById = getCustomerById;
		this.createDelivery = createDelivery;
		this.listDelivery = listDelivery;
		this.upadateDelivery = upadateDelivery;
		this.deleteDelivery = deleteDelivery;
		this.ensuresAprimaryAddress = ensuresAprimaryAddress;
		this.customerIsActiveDelivery = customerIsActiveDelivery;
	}
	
	@PostMapping
	public ResponseEntity<DeliveryListDto> createDelivery(Authentication authentication, @RequestBody @Valid DeliveryDto dto) {
		
		String id = authentication.getName();
		Optional<Customer> customer = getCustomerById.getCustomerById(id);
		
		ensuresAprimaryAddress.ensuresAprimaryAddress(customer.get().getCpf(), dto.main());
		customer.get().addNewDelivery(new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));

		if(dto.observation() != null) {
			Delivery delivery = createDelivery.createDelivery(customer.get().getCpf(), new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.get().getId());
			DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getId() ,delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep(), delivery.getObservation());
			return ResponseEntity.created(URI.create("/delivery/" + delivery.getCep()))
					   					 .body(deliveryListDto);
		}
		else {	
			Delivery delivery = createDelivery.createDelivery(customer.get().getCpf(), new Delivery(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.streetType(), dto.typeResidence(), dto.city(), dto.deliveryPhrase()));
			customerIsActiveDelivery.customerIsActiveDelivery(customer.get().getId());
			DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getId() ,delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep(), delivery.getObservation());
			return ResponseEntity.created(URI.create("/delivery/" + delivery.getId()))
  					 .body(deliveryListDto);
		}
	}
	
	@GetMapping
	public ResponseEntity<Page<DeliveryListDto>> listAllDeliveries(
			Authentication authentication, 
	        @PageableDefault(size = 10) Pageable pageable) {
	    
		String id = authentication.getName();
		Optional<Customer> customer = getCustomerById.getCustomerById(id);
	    List<Delivery> allDeliveries = listDelivery.listDelivery(customer.get().getId());

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allDeliveries.size());

	    if (start >= allDeliveries.size()) {
	        return ResponseEntity.noContent().build();
	    }

	    List<DeliveryListDto> paginatedDeliveries = allDeliveries.subList(start, end)
	            .stream()
	            .map(u -> new DeliveryListDto(u.getId() ,u.getReceiver(), u.getStreet(), u.getNumber(), u.getNeighborhood(), u.getCep(), u.getObservation()))
	            .toList();

	    Page<DeliveryListDto> page = new PageImpl<>(paginatedDeliveries, pageable, allDeliveries.size());

	    return ResponseEntity.ok(page);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<DeliveryListDto> updateDelivery(@PathVariable Long id, @RequestBody @Valid DeliveryUpdateDto dto) {
		Delivery delivery = upadateDelivery.updateDelivery(id, dto);
		DeliveryListDto deliveryListDto = new DeliveryListDto(delivery.getId(), delivery.getReceiver(), delivery.getStreet(), delivery.getNumber(), delivery.getNeighborhood(), delivery.getCep(), delivery.getObservation());
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
