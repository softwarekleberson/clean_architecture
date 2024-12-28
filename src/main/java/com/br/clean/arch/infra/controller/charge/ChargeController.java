package com.br.clean.arch.infra.controller.charge;

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
	public ChargeListDto createCharge(@PathVariable String cpf, @RequestBody @Valid ChargeDto dto) {
		Customer customer = getCustomer.getCustomerByCpf(cpf);
		ensuresAprimaryAddress.ensuresAprimaryCharge(cpf, dto.main());
		customer.addNewCharge(new Charge(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city()));

		Charge charge = createCharge.createCharge(cpf, new Charge(dto.main(), dto.receiver(), dto.street(), dto.number(), dto.neighborhood(), dto.cep(), dto.observation(), dto.streetType(), dto.typeResidence(), dto.city()));
		System.out.println(customer.getId() + "qqqqqqqqqqqq");
		customerIsActive.customerIsActive(customer.getId());
		return new ChargeListDto(charge.getReceiver(), charge.getStreet(), charge.getNumber(), charge.getNeighborhood(), charge.getCep());
	}
	
	@GetMapping("/{id}")
	public List<ChargeListDto> listAllCustomers(@PathVariable String id){
		return listCharge.listCharge(id).
			   stream().
			   map(u -> new ChargeListDto(u.getReceiver(), u.getStreet(), u.getNumber(), u.getNeighborhood(), u.getCep())).
			   collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ChargeListDto updateCharge(@PathVariable Long id, @RequestBody @Valid ChargeUpdateDto dto) {
		Charge charge = upadateCharge.updateCharge(id, dto);
		return new ChargeListDto(charge.getReceiver(), charge.getStreet(), charge.getNumber(), charge.getNeighborhood(), charge.getCep());
	}
	
	@DeleteMapping("/{id}")
	public void deleteCharge(@PathVariable Long id) {
		deleteCharge.deleteCharge(id);
	}
}
