package com.br.clean.arch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.br.clean.arch.application.gateways.customer.RepositoryCustomer;
import com.br.clean.arch.application.usecases.customer.ListCustomer;
import com.br.clean.arch.application.usecases.customer.UpdateCustomer;
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
	public CustomerRepositoryJpa customerRepositoryJpa(CustomerRepository repository, CustomerEntityMapper mapper, PasswordEncoder passwordEncoder) {
		return new CustomerRepositoryJpa(repository, mapper, passwordEncoder);
	}
	
	@Bean
	public CustomerEntityMapper customerEntityMapper() {
		return new CustomerEntityMapper();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
