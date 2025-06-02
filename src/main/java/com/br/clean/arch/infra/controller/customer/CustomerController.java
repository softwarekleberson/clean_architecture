package com.br.clean.arch.infra.controller.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.customer.GetCustomerById;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.application.usecases.customer.UpdateCustomer;
import com.br.clean.arch.application.usecases.customer.dto.input.UpdateCustomerCommand;
import com.br.clean.arch.application.usecases.customer.dto.output.CustomerOutputDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@RestController
@RequestMapping("/customer")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class); 

	private final ListCustomer listCustomer;
	private final UpdateCustomer updateCustomer;
	private final GetCustomerById getCustomerById;
	
	public CustomerController(ListCustomer listCustomer, UpdateCustomer updateCustomer, GetCustomerById getCustomerById) {
		this.listCustomer = listCustomer;
		this.updateCustomer = updateCustomer;
		this.getCustomerById = getCustomerById;
	}
	
	@GetMapping
	public ResponseEntity<Page<CustomerOutputDto>> getAllCustomers(
	 	@PageableDefault(size = 10, sort = "name") Pageable pageable) {
		
		logger.info("Iniciando tentativa de listagem de clientes: {}");

		Page<CustomerOutputDto> customerPage = listCustomer.listCustomers(pageable);
		
		logger.info("Listagem de cliente bem-sucedida : {}");
		return ResponseEntity.ok(customerPage);
	}

	@PutMapping
	public ResponseEntity<CustomerOutputDto> updateCustomer(Authentication authentication, @Valid @RequestBody UpdateCustomerCommand dto){ // Renomeado o método
		
		String id = authentication.getName();
		logger.info("Iniciando tentativa de atualização do cliente {}");
		
		CustomerOutputDto getCustomer = getCustomerById.getCustomerById(id);
		CustomerOutputDto customerOutputDto = updateCustomer.updateCustomer(getCustomer.id(), dto);		 	
		
		logger.info("Cliente atualizado com sucesso.");
		return ResponseEntity.status(HttpStatus.OK).body(customerOutputDto); 	
	}
}