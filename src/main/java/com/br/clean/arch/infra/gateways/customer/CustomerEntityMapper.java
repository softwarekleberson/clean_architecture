package com.br.clean.arch.infra.gateways.customer;

import com.br.clean.arch.domain.entitie.customer.Customer;
import com.br.clean.arch.domain.entitie.customer.valueObject.Email;
import com.br.clean.arch.domain.entitie.customer.valueObject.Gender;
import com.br.clean.arch.domain.entitie.customer.valueObject.Phone;
import com.br.clean.arch.infra.persistence.customer.CustomerEntity;
import com.br.clean.arch.infra.persistence.customer.EmailEntity;
import com.br.clean.arch.infra.persistence.customer.GenderEntity;
import com.br.clean.arch.infra.persistence.customer.PhoneEntity;

public class CustomerEntityMapper {

	 public static CustomerEntity toEntity(Customer domain) {
	        if (domain == null) {
	            return null;
	        }

	        return new CustomerEntity(
	            domain.getCpf(),
	            domain.getName(),
	            domain.getBirth(),
	            domain.getPassword(),
	            toEntity(domain.getGender()),
	            toEntity(domain.getPhone()),
	            toEntity(domain.getEmail())
	        );
	    }

	    public static Customer toDomain(CustomerEntity entity) {
	        if (entity == null) {
	            return null;
	        }

	        return new Customer(
	            entity.getCpf(),
	            entity.getName(),
	            entity.getBirth(),
	            toDomain(entity.getGenderEntity()),
	            toDomain(entity.getPhoneEntity()),
	            toDomain(entity.getEmailEntity())
	        );
	    }

	    private static GenderEntity toEntity(Gender domain) {
	        return domain == null ? null : GenderEntity.valueOf(domain.name());
	    }

	    private static Gender toDomain(GenderEntity entity) {
	        return entity == null ? null : Gender.valueOf(entity.name());
	    }

	    private static PhoneEntity toEntity(Phone domain) {
	        return domain == null ? null : new PhoneEntity(domain.getDdd(), domain.getPhone());
	    }

	    private static Phone toDomain(PhoneEntity entity) {
	        return entity == null ? null : new Phone(entity.getDdd(), entity.getPhone());
	    }

	    private static EmailEntity toEntity(Email domain) {
	        return domain == null ? null : new EmailEntity(domain.getEmail());
	    }

	    private static Email toDomain(EmailEntity entity) {
	        return entity == null ? null : new Email(entity.getEmail());
	    }
	}