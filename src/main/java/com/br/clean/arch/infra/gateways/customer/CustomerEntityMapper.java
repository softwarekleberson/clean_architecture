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

    public CustomerEntity toEntity(Customer domain) {
        if (domain == null) {
            return null;
        }

        // Este construtor cria uma NOVA instância de CustomerEntity.
        // É ideal para criação. Para atualização, preferimos 'updateEntityFromDomain'.
        return new CustomerEntity(
            domain.getId(),
            domain.getCpf(),
            domain.getName(),
            domain.getBirth(),
            domain.getPassword(),
            toEntity(domain.getGender()),
            toEntity(domain.getPhone()),
            toEntity(domain.getEmail())
        );
    }

    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Customer(
            entity.getId(),
            entity.isActive(),
            entity.getCpf(),
            entity.getName(),
            entity.getBirth(),
            toDomain(entity.getGenderEntity()),
            toDomain(entity.getPhoneEntity()),
            toDomain(entity.getEmailEntity()),
            entity.getPassword()
        );
    }

   
    public void updateEntityFromDomain(CustomerEntity entity, Customer domainCustomer) {
        if (entity == null || domainCustomer == null) {
            return;
        }

        entity.setName(domainCustomer.getName());
        entity.setBirth(domainCustomer.getBirth());
        entity.setPassword(domainCustomer.getPassword()); 
        entity.setActive(domainCustomer.isActive()); 

        entity.setGenderEntity(toEntity(domainCustomer.getGender()));
        entity.setPhoneEntity(toEntity(domainCustomer.getPhone()));
        entity.setEmailEntity(toEntity(domainCustomer.getEmail()));
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