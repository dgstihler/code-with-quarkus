package com.ismoke.infrastructure.services;

import com.ismoke.application.services.CustomerService;
import com.ismoke.domain.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class CustomerServiceFactory {
    private final CustomerRepository customerRepository;

    public CustomerServiceFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Produces
    @ApplicationScoped
    public CustomerService createCompradorService() {
        return new CustomerService(customerRepository);
    }


}


