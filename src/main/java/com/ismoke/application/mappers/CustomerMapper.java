package com.ismoke.application.mappers;

import com.ismoke.application.dtos.CustomerDTO;
import com.ismoke.domain.models.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getCpf(), customer.getName(), customer.getEmail());
    }
}
