package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Customer;
import com.ismoke.infrastructure.entities.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCpf(customer.getCpf());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(
            entity.getCpf(),
            entity.getName(),
            entity.getEmail()
        );
    }
}
