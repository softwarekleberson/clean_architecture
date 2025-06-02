package com.br.clean.arch.config.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.address.RepositoryDelivery;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.delivery.CreateDelivery;
import com.br.clean.arch.application.usecases.address.delivery.CustomerIsActiveDelivery;
import com.br.clean.arch.application.usecases.address.delivery.DeleteDelivery;
import com.br.clean.arch.application.usecases.address.delivery.EnsuresAprimaryDelivery;
import com.br.clean.arch.application.usecases.address.delivery.ListDelivery;
import com.br.clean.arch.application.usecases.address.delivery.UpdateDelivery;
import com.br.clean.arch.application.usecases.customer.GetCustomer;
import com.br.clean.arch.infra.gateways.address.DeliveryEntityMapper;
import com.br.clean.arch.infra.gateways.address.DeliveryRepositoryJpa;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.DeliveryRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class DeliveryConfig {

	@Bean
	public CreateDelivery createDelivery(RepositoryDelivery repositoriyDelivery, RepositoryCustomer repositoryCustomer) {
		return new CreateDelivery(repositoriyDelivery, repositoryCustomer);
	}
	
	@Bean
	public ListDelivery listDelivery(RepositoryDelivery repositoryDelivery, RepositoryCustomer repositoriyCustomer) {
		return new ListDelivery(repositoryDelivery, repositoriyCustomer);
	}
	
	@Bean
	public UpdateDelivery updateDelivery(RepositoryDelivery repositoryDelivery) {
		return new UpdateDelivery(repositoryDelivery);
	}
	
	@Bean
	public DeleteDelivery deleteDelivery(RepositoryDelivery repositoryDelivery) {
		return new DeleteDelivery(repositoryDelivery);
	}
	
	@Bean
	public EnsuresAprimaryDelivery ensuresAprimaryAddress (RepositoryDelivery repositoryDelivery) {
		return new EnsuresAprimaryDelivery(repositoryDelivery);
	}
	
	@Bean
	public CustomerIsActiveDelivery customerIsActiveDelivery (RepositoryDelivery repositoryDelivery) {
		return new CustomerIsActiveDelivery(repositoryDelivery);
	}
	
	@Bean
	public GetCustomer getCustomer(RepositoryCustomer repositoriyCustomer) {
		return new GetCustomer(repositoriyCustomer);
	}
	
	@Bean
    public DeliveryRepositoryJpa deliveryRepositoryJpa(CustomerRepository customerRepository,DeliveryRepository repository, DeliveryEntityMapper mapper, CustomerEntityMapper customerEntityMapper) {
        return new DeliveryRepositoryJpa(customerRepository, repository, mapper, customerEntityMapper);
    }
	
	@Bean
	public DeliveryEntityMapper deliveryEntityMapper() {
		return new DeliveryEntityMapper();
	}
}
