package com.example.carbigdata.core.service;

import com.example.carbigdata.core.domain.Customer;
import com.example.carbigdata.dataprovider.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void givenValidCustomer_whenCreateCustomer_thenCustomerIsSaved() {
        // Given
        Customer customer = new Customer(1L, "John Doe", "123456789");
        when(customerRepository.save(customer)).thenReturn(customer);

        // When
        Customer createdCustomer = customerService.createCustomer(customer);

        // Then
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void givenCustomerId_whenGetCustomer_thenReturnCustomer() {
        // Given
        Customer customer = new Customer(1L, "John Doe", "123456789");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // When
        Optional<Customer> foundCustomer = customerService.getCustomer(1L);

        // Then
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void whenGetAllCustomers_thenReturnCustomerList() {
        // Given
        Customer customer1 = new Customer(1L, "John Doe", "123456789");
        Customer customer2 = new Customer(2L, "Jane Doe", "987654321");
        List<Customer> customers = List.of(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        // When
        List<Customer> foundCustomers = customerService.getAllCustomers();

        // Then
        assertEquals(2, foundCustomers.size());
        assertEquals("John Doe", foundCustomers.get(0).getName());
        assertEquals("Jane Doe", foundCustomers.get(1).getName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void givenCustomerId_whenDeleteCustomer_thenVoid() {
        // Given
        doNothing().when(customerRepository).deleteById(1L);

        // When
        customerService.deleteCustomer(1L);

        // Then
        verify(customerRepository, times(1)).deleteById(1L);
    }
}