package com.example.carbigdata.api.controller;

import com.example.carbigdata.core.domain.Address;
import com.example.carbigdata.core.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Test
    void givenValidAddress_whenCreateAddress_thenReturnCreatedAddress() {
        // Given
        Address address = new Address(1L, "Street 1", "City", "12345");
        when(addressService.createAddress(address)).thenReturn(address);

        // When
        ResponseEntity<Address> response = addressController.createAddress(address);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(address, response.getBody());
        verify(addressService, times(1)).createAddress(address);
    }

    @Test
    void givenExistingId_whenGetAddress_thenReturnAddress() {
        // Given
        Address address = new Address(1L, "Street 1", "City", "12345");
        when(addressService.getAddress(1L)).thenReturn(Optional.of(address));

        // When
        ResponseEntity<Address> response = addressController.getAddress(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(address, response.getBody());
        verify(addressService, times(1)).getAddress(1L);
    }

    @Test
    void givenNonExistingId_whenGetAddress_thenReturnNotFound() {
        // Given
        when(addressService.getAddress(1L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Address> response = addressController.getAddress(1L);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(addressService, times(1)).getAddress(1L);
    }

    @Test
    void whenGetAllAddresses_thenReturnAddressList() {
        // Given
        Address address1 = new Address(1L, "Street 1", "City", "12345");
        Address address2 = new Address(2L, "Street 2", "City", "67890");
        List<Address> addresses = List.of(address1, address2);
        when(addressService.getAllAddresses()).thenReturn(addresses);

        // When
        List<Address> result = addressController.getAllAddresses();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(address1, result.get(0));
        assertEquals(address2, result.get(1));
        verify(addressService, times(1)).getAllAddresses();
    }

    @Test
    void givenId_whenDeleteAddress_thenNoContent() {
        // Given
        doNothing().when(addressService).deleteAddress(1L);

        // When
        ResponseEntity<Void> response = addressController.deleteAddress(1L);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(addressService, times(1)).deleteAddress(1L);
    }
}