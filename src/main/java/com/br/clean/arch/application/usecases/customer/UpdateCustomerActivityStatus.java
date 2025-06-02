package com.br.clean.arch.application.usecases.customer;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;

public class UpdateCustomerActivityStatus {

	private final RepositoryCustomer customerRepository;

	public UpdateCustomerActivityStatus(RepositoryCustomer customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public void customerIsActive(String id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("This customer not regitered yeat"));
		
		boolean hasCharge = !customer.getCharges().isEmpty();
		boolean hasDelivery = !customer.getDeliveries().isEmpty();
        boolean shouldBeActive = hasCharge && hasDelivery; 
		
		if(customer.isActive() != shouldBeActive) {
			customer.setActive(shouldBeActive);
			this.customerRepository.save(customer);
		}
	}
}
