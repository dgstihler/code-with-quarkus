package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Customer;
import com.ismoke.domain.repositories.CustomerRepository;
import com.ismoke.infrastructure.entities.CustomerEntity;
import com.ismoke.infrastructure.mappers.CustomerMapper;
import com.ismoke.infrastructure.mappers.SellerMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository, PanacheRepositoryBase<CustomerEntity, String> {

    @Transactional
    @Override
    public void save(Customer customer) {
        CustomerEntity entity = CustomerMapper.toEntity(customer);
        persist(entity);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return Optional.ofNullable(findById(cpf))
            .map(CustomerMapper::toDomain);
    }

    @Transactional
    @Override
    public void delete(String cpf) {
        deleteById(cpf);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return listAll().stream()
            .map(CustomerMapper::toDomain)
            .toList();
    }
}
