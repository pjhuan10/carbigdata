package com.example.carbigdata.repository;

import com.example.carbigdata.entity.Customer;
import com.example.carbigdata.entity.Occurrence;
import com.example.carbigdata.entity.OccurrenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

    List<Occurrence> findByCustomer(Customer customer);

    List<Occurrence> findByOccurrenceDate(LocalDate occurrenceDate);

    List<Occurrence> findByAddress_City(String city);

    List<Occurrence> findAllByOrderByOccurrenceDateAsc();

    List<Occurrence> findAllByOrderByOccurrenceDateDesc();

    List<Occurrence> findByStatus(OccurrenceStatus status);

    List<Occurrence> findByCustomerAndStatus(Customer customer, OccurrenceStatus status);

}
