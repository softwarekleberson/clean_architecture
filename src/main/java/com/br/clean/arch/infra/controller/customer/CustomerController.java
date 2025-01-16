package com.br.clean.arch.infra.controller.customer;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.application.usecases.customer.UpdateCustomer;
import com.br.clean.arch.application.usecases.password.AuthenticateUser;
import com.br.clean.arch.domain.entitie.customer.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final ListCustomer listCustomer;
	private final UpdateCustomer updateCustomer;
	private final AuthenticateUser authenticateUser;
	
	public CustomerController(CreateCustomer createCustomer, ListCustomer listCustomer, UpdateCustomer updateCustomer, AuthenticateUser authenticateUser) {
		this.createCustomer = createCustomer;
		this.listCustomer = listCustomer;
		this.updateCustomer = updateCustomer;
		this.authenticateUser = authenticateUser;
	}
	
	@PostMapping("/register")
	public ResponseEntity<CustomerListDto> createCustomer(@RequestBody @Valid CustomerDto dto) {
	   		Customer customer = createCustomer.createCustomer(
	        new Customer(
	            dto.cpf(), 
	            dto.name(), 
	            dto.birth(), 
	            dto.password(), 
	            dto.confirmPassword(), 
                dto.gender(), 
                dto.phone(), 
                dto.email()
	         )
	        );
	   		
	   	CustomerListDto customerListDto = new CustomerListDto(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail());
	   	return ResponseEntity.created(URI.create("register/customer/" + customer.getId())).body(customerListDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto){
		Customer customer = authenticateUser.authenticate(dto.email().getEmail(), dto.password());
		return ResponseEntity.created(URI.create("login/customer/" + customer.getId())).body(customer); 
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
	            .map(u -> new CustomerListDto(u.getId(), u.getCpf(), u.getName(), u.getEmail()))
	            .toList();

	    Page<CustomerListDto> page = new PageImpl<>(paginatedCustomers, pageable, allCustomers.size());

	    return ResponseEntity.ok(page);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerListDto> updateAllCustomer(@PathVariable String id, @Valid @RequestBody CustomerUpdateDto dto){
		Customer customer = updateCustomer.updateCustomer(id, dto);
	   	CustomerListDto customerListDto = new CustomerListDto(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail());
	   	return ResponseEntity.ok(customerListDto);
	}
	
}
