package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.clean.arch.application.gateways.address.RepositoryCharge;
import com.br.clean.arch.application.usecases.address.charge.CreateCharge;
import com.br.clean.arch.application.usecases.address.charge.CustomerIsActive;
import com.br.clean.arch.application.usecases.address.charge.DeleteCharge;
import com.br.clean.arch.application.usecases.address.charge.EnsuresAprimaryCharge;
import com.br.clean.arch.application.usecases.address.charge.ListCharge;
import com.br.clean.arch.application.usecases.address.charge.UpdateCharge;
import com.br.clean.arch.infra.gateways.address.ChargeEntityMapper;
import com.br.clean.arch.infra.gateways.address.ChargeRepositoryJpa;
import com.br.clean.arch.infra.persistence.address.delivery.ChargeRepository;
import com.br.clean.arch.infra.persistence.customer.CustomerRepository;

@Configuration
public class ChargeConfig {

	@Bean
	public CreateCharge createCharge(RepositoryCharge repositoriyCharge) {
		return new CreateCharge(repositoriyCharge);
	}
	
	@Bean
	public ListCharge listCharge(RepositoryCharge repositoryCharge) {
		return new ListCharge(repositoryCharge);
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
	public CustomerIsActive customerIsActive (RepositoryCharge repositoryCharge) {
		return new CustomerIsActive(repositoryCharge);
	}
	
	@Bean
	public ChargeRepositoryJpa chargeRepositoryJpa(CustomerRepository customerRepository, ChargeRepository repository, ChargeEntityMapper mapper) {
	    return new ChargeRepositoryJpa(customerRepository, repository, mapper);  
	}
	
	@Bean
	public ChargeEntityMapper chargeEntityMapper() {
		return new ChargeEntityMapper();
	}
}
