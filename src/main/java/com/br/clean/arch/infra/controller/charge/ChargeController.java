package com.br.clean.arch.infra.controller.charge;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus; // Adicionado para @ResponseStatus

import com.br.clean.arch.application.usecases.address.charge.CreateCharge;
import com.br.clean.arch.application.usecases.address.charge.DeleteCharge;
import com.br.clean.arch.application.usecases.address.charge.EnsuresAprimaryCharge;
import com.br.clean.arch.application.usecases.address.charge.ListCharge;
import com.br.clean.arch.application.usecases.address.charge.UpdateCharge;
import com.br.clean.arch.application.usecases.address.charge.dto.input.CreateChargeCommand;
import com.br.clean.arch.application.usecases.address.charge.dto.input.UpdateChargeCommand;
import com.br.clean.arch.application.usecases.address.charge.dto.output.ChargeOutputDto;
import com.br.clean.arch.application.usecases.customer.UpdateCustomerActivityStatus;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;
import com.br.clean.arch.application.usecases.customer.GetCustomerById;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customer/charges")
@SecurityRequirement(name = "bearer-key")
public class ChargeController {

	private static final Logger logger = LoggerFactory.getLogger(ChargeController.class);

	private final GetCustomerById getCustomerById;
	private final CreateCharge createCharge;
	private final ListCharge listCharge;
	private final UpdateCharge updateCharge;
	private final DeleteCharge deleteCharge;
	private final EnsuresAprimaryCharge ensuresAprimaryCharge;
	private final UpdateCustomerActivityStatus customerIsActive;

	public ChargeController(GetCustomerById getCustomerById, CreateCharge createCharge, ListCharge listCharge, UpdateCharge updateCharge, DeleteCharge deleteCharge, EnsuresAprimaryCharge ensuresAprimaryCharge, UpdateCustomerActivityStatus customerIsActive) {
		this.getCustomerById = getCustomerById;
		this.createCharge = createCharge;
		this.listCharge = listCharge;
		this.updateCharge = updateCharge;
		this.deleteCharge = deleteCharge;
		this.ensuresAprimaryCharge = ensuresAprimaryCharge;
		this.customerIsActive = customerIsActive;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<ChargeOutputDto> createCharge(@RequestBody @Valid CreateChargeCommand dto, Authentication authentication) {

		String id = authentication.getName();
		logger.info("Iniciando tentativa de criação de endereço de cobrança para o cliente ID: {}", id);

		CustomerOutputDto customerListDto = getCustomerById.getCustomerById(id);

		ensuresAprimaryCharge.ensuresAprimaryCharge(customerListDto.cpf(), dto.main());
		ChargeOutputDto chargeOutputDto = createCharge.createCharge(customerListDto.cpf(), dto);
		customerIsActive.customerIsActive(customerListDto.id());

		logger.info("Endereço de cobrança criado com sucesso para o cliente ID: {}. ID do endereço: {}", id, chargeOutputDto.id());
		return ResponseEntity.created(URI.create("/customer/charges/" + chargeOutputDto.id()))
			   .body(chargeOutputDto);
	}

	@GetMapping
	public ResponseEntity<Page<ChargeOutputDto>> listAllCharges(
	 	Authentication authentication,
	 	@PageableDefault(size = 10) Pageable pageable) {

		String id = authentication.getName();
		logger.info("Iniciando tentativa de listagem de endereços de cobrança para o cliente ID: {}", id);

		CustomerOutputDto customerOptDto = getCustomerById.getCustomerById(id);

		Page<ChargeOutputDto> chargesPage = listCharge.listCharge(customerOptDto.cpf(), pageable);

		logger.info("Listagem de endereços de cobrança bem-sucedida para o cliente ID: {}", id);
		return ResponseEntity.ok(chargesPage);
	}

	@PutMapping
	public ResponseEntity<ChargeOutputDto> updateCharge(@RequestBody @Valid UpdateChargeCommand dto) {

		logger.info("Iniciando tentativa de atualização do endereço de cobrança com ID: {}", dto.id());

		ChargeOutputDto chargeListDto = updateCharge.updateCharge(dto.id(), dto);

		logger.info("Endereço de cobrança com ID {} atualizado com sucesso.", dto.id());
		return ResponseEntity.ok(chargeListDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public ResponseEntity<Void> deleteCharge(@PathVariable Long id) {

		logger.info("Iniciando tentativa de exclusão do endereço de cobrança com ID: {}", id);

		deleteCharge.deleteCharge(id);

		logger.info("Endereço de cobrança com ID {} excluído com sucesso.", id);
		return ResponseEntity.noContent().build();
	}
}
