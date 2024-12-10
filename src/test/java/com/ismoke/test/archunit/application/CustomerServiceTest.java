package com.ismoke.test.archunit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ismoke.application.dtos.CustomerDTO;
import com.ismoke.application.services.CustomerService;
import com.ismoke.domain.models.Customer;
import com.ismoke.domain.repositories.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CustomerServiceTest {

    private final CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
    private final CustomerService customerService = new CustomerService(customerRepository);

    @Test
    void shouldCreateCompradorAndReturnDTO() {

        CustomerDTO customerDTO = customerService.createCustomer("05555982925", "Diogo Galdino", "dgstihler@gmail.com");

        assertEquals("05555982925", customerDTO.getCpf());
        assertEquals("Diogo Galdino", customerDTO.getName());
        assertEquals("dgstihler@gmail.com", customerDTO.getEmail());
    }

    @Test
    void shouldListAllCompradoresAndMapToDTOs() {
        // Arrange
        List<Customer> mockCompradores = List.of(
            new Customer("12345678900", "Diogo", "galdino@email.com"),
            new Customer("98765432100", "Galdino", "diogo@email.com")
        );
        when(customerRepository.listAllCustomers()).thenReturn(mockCompradores);

        // Act
        List<CustomerDTO> customers = customerService.listAllCustomers();

        // Assert
        assertEquals(2, customers.size());
        assertEquals("12345678900", customers.get(0).getCpf());
        assertEquals("Diogo", customers.get(0).getName());
        assertEquals("galdino@email.com", customers.get(0).getEmail());

        assertEquals("98765432100", customers.get(1).getCpf());
        assertEquals("Galdino", customers.get(1).getName());
        assertEquals("diogo@email.com", customers.get(1).getEmail());

    }

    @Test
    void shouldFindCompradorByCpfAndMapToDTO() {
        // Arrange
        Customer mockCustomer = new Customer("12345678900", "Galdino", "dgstihler@gmail.com");
        when(customerRepository.findByCpf("12345678900")).thenReturn(java.util.Optional.of(mockCustomer));

        // Act
        Optional<CustomerDTO> customerOptional = customerService.findByCpf("12345678900");

        // Assert
        assertTrue(customerOptional.isPresent());
        CustomerDTO customerDTO = customerOptional.get();
        assertEquals("12345678900", customerDTO.getCpf());
        assertEquals("Galdino", customerDTO.getName());
        assertEquals("dgstihler@gmail.com", customerDTO.getEmail());
    }

    @Test
    void shouldReturnEmptyWhenCompradorNotFoundByCpf() {
        // Arrange
        when(customerRepository.findByCpf("12345678900")).thenReturn(java.util.Optional.empty());

        // Act
        Optional<CustomerDTO> compradorOptional = customerService.findByCpf("12345678900");

        // Assert
        assertFalse(compradorOptional.isPresent());
    }

}
