package com.br.clean.arch.infra.controller.charge;

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

import com.br.clean.arch.application.usecases.address.charge.CreateCharge;
import com.br.clean.arch.application.usecases.address.charge.CustomerIsActive;
import com.br.clean.arch.application.usecases.address.charge.DeleteCharge;
import com.br.clean.arch.application.usecases.address.charge.EnsuresAprimaryCharge;
import com.br.clean.arch.application.usecases.address.charge.ListCharge;
import com.br.clean.arch.application.usecases.address.charge.UpdateCharge;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.domain.entitie.address.Charge;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/charge")
public class ChargeController {

	private final GetCustomer getCustomer;
	private final CreateCharge createCharge;
	private final ListCharge listCharge;
	private final UpdateCharge upadateCharge;
	private final DeleteCharge deleteCharge;
	private final EnsuresAprimaryCharge ensuresAprimaryAddress;
	private final CustomerIsActive customerIsActive;
	
	public ChargeController(GetCustomer getCustomer, CreateCharge createCharge, ListCharge listCharge, UpdateCharge upadateCharge, DeleteCharge deleteCharge, EnsuresAprimaryCharge ensuresAprimaryAddress, CustomerIsActive customerIsActive) {
		this.getCustomer = getCustomer;
		this.createCharge = createCharge;
		this.listCharge = listCharge;
		this.upadateCharge = upadateCharge;
		this.deleteCharge = deleteCharge;
		this.ensuresAprimaryAddress = ensuresAprimaryAddress;
		this.customerIsActive = customerIsActive;
	}
	
	@PostMapping("/{cpf}")
	public ResponseEntity<ChargeListDto> createCharge(@PathVariable String cpf, @RequestBody @Valid ChargeDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		ensuresAprimaryAddress.ensuresAprimaryCharge(cpf, dto.main());
		customer.addNewCharge(new Charge(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city()));

		Charge charge = createCharge.createCharge(cpf, new Charge(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city()));
		customerIsActive.customerIsActive(customer.getId());
		ChargeListDto chargeListDto = new ChargeListDto(charge.getId(), charge.getReceiver(), charge.getStreet(), charge.getNumber(), charge.getNeighborhood(), charge.getCep());
	
		return ResponseEntity.created(URI.create("/charge/" + charge.getCep()))
			   .body(chargeListDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<ChargeListDto>> listAllCustomers(
	        @PathVariable String id, 
	        @PageableDefault(size = 10) Pageable pageable) {

	    List<Charge> allCharges = listCharge.listCharge(id);

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allCharges.size());

	    if (start >= allCharges.size()) {
	        return ResponseEntity.noContent().build(); 
	    }

	    List<ChargeListDto> paginatedCharges = allCharges.subList(start, end)
	            .stream()
	            .map(u -> new ChargeListDto(
	                    u.getId(),
	                    u.getReceiver(),
	                    u.getStreet(),
	                    u.getNumber(),
	                    u.getNeighborhood(),
	                    u.getCep()))
	            .toList();

	    Page<ChargeListDto> page = new PageImpl<>(paginatedCharges, pageable, allCharges.size());

	    return ResponseEntity.ok(page);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChargeListDto> updateCharge(@PathVariable Long id, @RequestBody @Valid ChargeUpdateDto dto) {
		Charge charge = upadateCharge.updateCharge(id, dto);
		ChargeListDto chargeListDto = new ChargeListDto(charge.getId() ,charge.getReceiver(), charge.getStreet(), charge.getNumber(), charge.getNeighborhood(), charge.getCep());
		return ResponseEntity.ok(chargeListDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCharge(@PathVariable Long id) {
		try {
			deleteCharge.deleteCharge(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
