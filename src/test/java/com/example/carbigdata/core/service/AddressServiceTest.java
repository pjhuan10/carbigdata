package com.example.carbigdata.core.service;

import com.example.carbigdata.core.domain.Address;
import com.example.carbigdata.dataprovider.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
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
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1L);
        address.setStreet("Rua A");
        address.setCity("Cidade X");
        address.setState("Estado Y");
    }

    @Test
    void givenAddress_whenCreateAddress_thenAddressIsSaved() {
        // Given
        when(addressRepository.save(address)).thenReturn(address);

        // When
        Address createdAddress = addressService.createAddress(address);

        // Then
        assertNotNull(createdAddress);
        assertEquals(address.getStreet(), createdAddress.getStreet());
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void givenAddressId_whenGetAddress_thenAddressIsReturned() {
        // Given
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        // When
        Optional<Address> foundAddress = addressService.getAddress(1L);

        // Then
        assertTrue(foundAddress.isPresent());
        assertEquals(address.getStreet(), foundAddress.get().getStreet());
    }

    @Test
    void givenNoAddress_whenGetAllAddresses_thenEmptyListReturned() {
        // Given
        when(addressRepository.findAll()).thenReturn(List.of());

        // When
        List<Address> addresses = addressService.getAllAddresses();

        // Then
        assertNotNull(addresses);
        assertTrue(addresses.isEmpty());
    }

    @Test
    void givenAddressId_whenDeleteAddress_thenAddressIsDeleted() {
        // Given
        doNothing().when(addressRepository).deleteById(1L);

        // When
        addressService.deleteAddress(1L);

        // Then
        verify(addressRepository, times(1)).deleteById(1L);
    }

}