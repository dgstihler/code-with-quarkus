package com.ismoke.application.services;

import com.ismoke.application.dtos.CustomerDTO;
import com.ismoke.application.mappers.CustomerMapper;
import com.ismoke.domain.models.Customer;
import com.ismoke.domain.repositories.CustomerRepository;
import com.ismoke.domain.exceptions.CustomersNotFoundException;
import com.ismoke.domain.exceptions.DeleteCustomerException;
import com.ismoke.domain.exceptions.PersistCustomerException;
import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(String cpf, String nome, String email) {
        try {
            Customer customer = new Customer(cpf, nome, email);
            customerRepository.save(customer);
            return CustomerMapper.toDTO(customer);
        } catch (RuntimeException e) {
            throw new PersistCustomerException("Erro ao salvar comprador no banco de dados.", e);
        }
    }

    public List<CustomerDTO> listAllCustomers() {

        try {
            List<CustomerDTO> customers = customerRepository.listAllCustomers().stream()
                .map(customer ->
                    new CustomerDTO(
                        customer.getCpf(),
                        customer.getName(),
                        customer.getEmail()))
                .toList();

            if (customers.isEmpty()) {
                throw new RuntimeException("Nenhum comprador encontrado.");
            }
            return customers;
        } catch (RuntimeException e) {
            throw new CustomersNotFoundException("Erro ao buscar compradores no banco de dados.", e);
        }
    }

    public Optional<CustomerDTO> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).map(customer ->
            new CustomerDTO(
                customer.getCpf(),
                customer.getName(),
                customer.getEmail()));
    }

    public void delete(String cpf) {
        try {
            customerRepository.delete(cpf);
        } catch (RuntimeException e) {
            throw new DeleteCustomerException("Erro ao deletar comprador no banco de dados.", e);
        }
    }
}


