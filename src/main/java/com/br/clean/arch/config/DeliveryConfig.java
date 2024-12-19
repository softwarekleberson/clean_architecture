package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.gateways.customer.RepositoriyCustomer;
import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.infra.gateways.address.DeliveryEntityMapper;
import com.br.clean.arch.infra.gateways.address.DeliveryRepositoryJpa;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class DeliveryConfig {

	@Bean
	public CreateDelivery createDelivery(RepositoryDelivery repositoriyDelivery) {
		return new CreateDelivery(repositoriyDelivery);
	}
	
	@Bean
	public GetCustomer getCustomer(RepositoriyCustomer repositoriyCustomer) {
		return new GetCustomer(repositoriyCustomer);
	}
	
	@Bean
    public DeliveryRepositoryJpa deliveryRepositoryJpa(CustomerRepository customerRepository,DeliveryRepository repository, DeliveryEntityMapper mapper) {
        return new DeliveryRepositoryJpa(customerRepository, repository, mapper);
    }
	
	@Bean
	public DeliveryEntityMapper deliveryEntityMapper() {
		return new DeliveryEntityMapper();
	}
}
