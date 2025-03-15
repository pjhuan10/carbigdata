package com.example.carbigdata.dataprovider.repository;

import com.example.carbigdata.core.domain.Address;
import com.example.carbigdata.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomer(Customer customer);

    List<Address> findByCity(String city);
}
