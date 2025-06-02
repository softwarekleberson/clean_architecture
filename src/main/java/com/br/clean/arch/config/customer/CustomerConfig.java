package com.br.clean.arch.config.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.GetCustomerById;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.application.usecases.customer.UpdateCustomer;
import com.br.clean.arch.application.usecases.customer.UpdateCustomerActivityStatus;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.gateways.customer.CustomerRepositoryJpa;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class CustomerConfig {

    @Bean
    public ListCustomer listCustomer(RepositoryCustomer repositoriyCustomer) {
        return new ListCustomer(repositoriyCustomer);
    }
    
    @Bean
    public UpdateCustomer updateCustomer(RepositoryCustomer repositoriyCustomer) {
        return new UpdateCustomer(repositoriyCustomer);
    }
    
    @Bean
    public GetCustomerById getCustomerById(RepositoryCustomer repositoriyCustomer) {
        return new GetCustomerById(repositoriyCustomer);
    }
    
    @Bean
	public UpdateCustomerActivityStatus customerIsActive (RepositoryCustomer repositoryCharge) {
		return new UpdateCustomerActivityStatus(repositoryCharge);
	}
    
    @Bean
    public CustomerRepositoryJpa customerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper) {
        return new CustomerRepositoryJpa(repository, mapper);
    }
    
    @Bean
    public CustomerEntityMapper customerEntityMapper() {
        return new CustomerEntityMapper();
    }
}
