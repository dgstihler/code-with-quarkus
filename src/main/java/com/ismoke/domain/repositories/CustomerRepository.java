package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);

    Optional<Customer> findByCpf(String cpf);

    List<Customer> listAllCustomers();

    void delete(String cpf);
}
