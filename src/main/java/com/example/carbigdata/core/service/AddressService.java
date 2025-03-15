package com.example.carbigdata.core.service;

import com.example.carbigdata.core.domain.Address;
import com.example.carbigdata.dataprovider.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> getAddress(Long id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
