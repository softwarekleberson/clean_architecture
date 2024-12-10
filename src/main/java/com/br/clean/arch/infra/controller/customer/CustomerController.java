package com.br.clean.arch.infra.controller.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CreateCustomer createCustomer;
	private final ListCustomer listCustomer;
	
	public CustomerController(CreateCustomer createCustomer, ListCustomer listCustomer) {
		this.createCustomer = createCustomer;
		this.listCustomer = listCustomer;
	}
	
	   	@PostMapping
	    public CustomerListDto createCustomer(@RequestBody CustomerDto dto) {
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
	        return new CustomerListDto(customer.getCpf(), customer.getName(), customer.getEmail());
	    }
	
	@GetMapping
	public List<CustomerListDto> getAllCustomers(){
		return listCustomer.listCustomers().
			   stream().
			   map(u -> new CustomerListDto(u.getCpf(), u.getName(), u.getEmail())).
			   collect(Collectors.toList());
	}
}
