package com.example.carbigdata.api.controller;

import static org.junit.jupiter.api.Assertions.*;


import com.example.carbigdata.core.domain.Customer;
import com.example.carbigdata.core.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void givenValidCustomer_whenCreateCustomer_thenReturnCreatedCustomer() {
        // Given
        Customer customer = new Customer(1L, "John Doe", "12345678901");
        when(customerService.createCustomer(customer)).thenReturn(customer);

        // When
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    void givenCustomerId_whenGetCustomer_thenReturnCustomer() {
        // Given
        Customer customer = new Customer(1L, "John Doe", "12345678901");
        when(customerService.getCustomer(1L)).thenReturn(Optional.of(customer));

        // When
        ResponseEntity<Customer> response = customerController.getCustomer(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).getCustomer(1L);
    }

    @Test
    void givenNonExistingCustomerId_whenGetCustomer_thenReturnNotFound() {
        // Given
        when(customerService.getCustomer(1L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Customer> response = customerController.getCustomer(1L);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(customerService, times(1)).getCustomer(1L);
    }

    @Test
    void whenGetAllCustomers_thenReturnCustomerList() {
        // Given
        Customer customer1 = new Customer(1L, "John Doe", "12345678901");
        Customer customer2 = new Customer(2L, "Jane Smith", "23456789012");
        List<Customer> customers = List.of(customer1, customer2);
        when(customerService.getAllCustomers()).thenReturn(customers);

        // When
        List<Customer> foundCustomers = customerController.getAllCustomers();

        // Then
        assertNotNull(foundCustomers);
        assertEquals(2, foundCustomers.size());
        assertEquals("John Doe", foundCustomers.get(0).getName());
        assertEquals("Jane Smith", foundCustomers.get(1).getName());
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void whenGetAllCustomersWithPagination_thenReturnPagedCustomerList() {
        // Given
        Customer customer1 = new Customer(1L, "John Doe", "12345678901");
        Customer customer2 = new Customer(2L, "Jane Smith", "23456789012");
        Page<Customer> customersPage = mock(Page.class);
        when(customerService.getAllCustomers(any(Pageable.class))).thenReturn((List<Customer>) customersPage);

        // When
        Page<Customer> foundCustomers = customerController.getAllCustomers(Pageable.unpaged());

        // Then
        assertNotNull(foundCustomers);
        verify(customerService, times(1)).getAllCustomers(any(Pageable.class));
    }

    @Test
    void givenCustomerId_whenDeleteCustomer_thenNoContent() {
        // Given
        doNothing().when(customerService).deleteCustomer(1L);

        // When
        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}