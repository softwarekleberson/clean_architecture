package com.br.clean.arch.infra.controller.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.infra.controller.customer.input.CustomerUpdateDto;
import com.br.clean.arch.infra.controller.customer.output.CustomerListDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {

	private final ListCustomer listCustomer;
	private final UpdateCustomer updateCustomer;
	private final GetCustomerById getCustomerById;
	
	public CustomerController(ListCustomer listCustomer, UpdateCustomer updateCustomer, GetCustomerById getCustomerById) {
		this.listCustomer = listCustomer;
		this.updateCustomer = updateCustomer;
		this.getCustomerById = getCustomerById;
	}
	
	@GetMapping
	public ResponseEntity<Page<CustomerListDto>> getAllCustomers(
	        @PageableDefault(size = 10, sort = "name") Pageable pageable) {
	    
	    List<Customer> allCustomers = listCustomer.listCustomers();
	    
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), allCustomers.size());

	    if (start >= allCustomers.size()) {
	        return ResponseEntity.noContent().build(); 
	    }

	    List<CustomerListDto> paginatedCustomers = allCustomers.subList(start, end)
	            .stream()
	            .map(u -> new CustomerListDto(u.getId(), u.getCpf(), u.getName(), u.getEmail(), u.isActive()))
	            .toList();

	    Page<CustomerListDto> page = new PageImpl<>(paginatedCustomers, pageable, allCustomers.size());

	    return ResponseEntity.ok(page);
	}

	@PutMapping
	public ResponseEntity<CustomerListDto> updateAllCustomer(Authentication authentication, @Valid @RequestBody CustomerUpdateDto dto){
		String id = authentication.getName();
		Optional<Customer> getCustomer = getCustomerById.getCustomerById(id);
		
		Customer customer = updateCustomer.updateCustomer(getCustomer.get().getId(), dto);
	   	CustomerListDto customerListDto = new CustomerListDto(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail(), customer.isActive());
	   	return ResponseEntity.ok(customerListDto);
	}
}
