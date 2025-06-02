package com.br.clean.arch.infra.controller.delivery;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.address.delivery.CustomerIsActiveDelivery;
import com.br.clean.arch.application.usecases.address.delivery.DeleteDelivery;
import com.br.clean.arch.application.usecases.address.delivery.EnsuresAprimaryDelivery;
import com.br.clean.arch.application.usecases.address.delivery.ListDelivery;
import com.br.clean.arch.application.usecases.address.delivery.UpdateDelivery;
import com.br.clean.arch.application.usecases.address.delivery.dto.input.CreateDeliveryCommand;
import com.br.clean.arch.application.usecases.address.delivery.dto.input.UpdateDeliveryCommand;
import com.br.clean.arch.application.usecases.address.delivery.dto.output.DeliveryOutputDto;
import com.br.clean.arch.application.usecases.customer.GetCustomerById;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customer/deliveries")
@SecurityRequirement(name = "bearer-key")
public class DeliveryController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class); // Adicionado logger

	private final GetCustomerById getCustomerById;
	private final CreateDelivery createDelivery;
	private final ListDelivery listDelivery;
	private final UpdateDelivery updateDelivery; 
	private final DeleteDelivery deleteDelivery;
	private final EnsuresAprimaryDelivery ensuresAprimaryDelivery; 
	private final CustomerIsActiveDelivery customerIsActiveDelivery;
	
	public DeliveryController(GetCustomerById getCustomerById, CreateDelivery createDelivery, ListDelivery listDelivery, UpdateDelivery updateDelivery, DeleteDelivery deleteDelivery, EnsuresAprimaryDelivery ensuresAprimaryDelivery, CustomerIsActiveDelivery customerIsActiveDelivery) {
		this.getCustomerById = getCustomerById;
		this.createDelivery = createDelivery;
		this.listDelivery = listDelivery;
		this.updateDelivery = updateDelivery; 
		this.deleteDelivery = deleteDelivery;
		this.ensuresAprimaryDelivery = ensuresAprimaryDelivery; 
		this.customerIsActiveDelivery = customerIsActiveDelivery;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<DeliveryOutputDto> createDelivery(Authentication authentication, @RequestBody @Valid CreateDeliveryCommand dto) {
		
		String id = authentication.getName();
		logger.info("Iniciando tentativa de criação de entrega de cobrança para o cliente ID: {}", id);

		CustomerOutputDto customerDtoOptional = getCustomerById.getCustomerById(id);		
						
		ensuresAprimaryDelivery.ensuresAprimaryAddress(customerDtoOptional.cpf(), dto.main()); 
		DeliveryOutputDto deliveryOutputDto = createDelivery.createDelivery(customerDtoOptional.cpf(), dto);	
		customerIsActiveDelivery.customerIsActiveDelivery(customerDtoOptional.id());
						
		logger.info("Endereço de entrega criado com sucesso para o cliente ID: {}. ID do endereço: {}", id, deliveryOutputDto.id());
		return ResponseEntity.created(URI.create("/customer/deliveries/" + deliveryOutputDto.id())) 
			   .body(deliveryOutputDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DeliveryOutputDto>> listAllDeliveries(
			Authentication authentication,@PageableDefault(size = 10) Pageable pageable) {
	 
		String id = authentication.getName();
		logger.info("Iniciando tentativa de listagem de endereços de entrega para o cliente ID: {}", id);
		
		CustomerOutputDto customerOptDto = getCustomerById.getCustomerById(id);
			
	    Page<DeliveryOutputDto> deliveriesPage = listDelivery.listDelivery(customerOptDto.cpf(), pageable);
	    
		logger.info("Listagem de endereços de entrega bem-sucedida para o cliente ID: {}", id);
	    return ResponseEntity.ok(deliveriesPage);
	}
	
	@PutMapping
	public ResponseEntity<DeliveryOutputDto> updateDelivery(@RequestBody @Valid UpdateDeliveryCommand dto) {
		
		logger.info("Iniciando tentativa de atualização do endereço de entrega com ID: {}", dto.id());
		
		DeliveryOutputDto deliveryListDto = updateDelivery.updateDelivery(dto.id(), dto); 
		
		logger.info("Endereço de entrega com ID {} atualizado com sucesso.", dto.id());
		return ResponseEntity.ok(deliveryListDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
		
		logger.info("Iniciando tentativa de exclusão do endereço de entrega com ID: {}", id);
		
		deleteDelivery.deleteDelivery(id);
		
		logger.info("Endereço de entrega com ID {} excluído com sucesso.", id);
		return ResponseEntity.noContent().build();
	}
}