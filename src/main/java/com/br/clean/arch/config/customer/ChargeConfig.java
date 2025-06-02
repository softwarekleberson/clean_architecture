package com.br.clean.arch.config.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.address.charge.CreateCharge;
import com.br.clean.arch.application.usecases.address.charge.DeleteCharge;
import com.br.clean.arch.application.usecases.address.charge.EnsuresAprimaryCharge;
import com.br.clean.arch.application.usecases.address.charge.ListCharge;
import com.br.clean.arch.application.usecases.address.charge.UpdateCharge;
import com.br.clean.arch.infra.gateways.address.ChargeEntityMapper;
import com.br.clean.arch.infra.gateways.address.ChargeRepositoryJpa;
import com.br.clean.arch.infra.gateways.customer.CustomerEntityMapper;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class ChargeConfig {

	@Bean
	public CreateCharge createCharge(RepositoryCharge repositoriyCharge, RepositoryCustomer repositoriyCustomer) {
		return new CreateCharge(repositoriyCharge, repositoriyCustomer);
	}
	
	@Bean
	public ListCharge listCharge(RepositoryCharge repositoryCharge, RepositoryCustomer repositoriyCustomer) {
		return new ListCharge(repositoryCharge, repositoriyCustomer);
	}
	
	@Bean
	public UpdateCharge updateCharge(RepositoryCharge repositoryCharge) {
		return new UpdateCharge(repositoryCharge);
	}
	
	@Bean
	public DeleteCharge deleteCharge(RepositoryCharge repositoryCharge) {
		return new DeleteCharge(repositoryCharge);
	}
	
	@Bean
	public EnsuresAprimaryCharge ensuresAprimaryCharge (RepositoryCharge repositoryCharge) {
		return new EnsuresAprimaryCharge(repositoryCharge);
	}
	
	@Bean
	public ChargeRepositoryJpa chargeRepositoryJpa(CustomerRepository customerRepository, ChargeRepository repository, ChargeEntityMapper mapper, CustomerEntityMapper customerEntityMapper) {
	    return new ChargeRepositoryJpa(customerRepository, repository, mapper, customerEntityMapper);  
	}
	
	@Bean
	public ChargeEntityMapper chargeEntityMapper() {
		return new ChargeEntityMapper();
	}
}
