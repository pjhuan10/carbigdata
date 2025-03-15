package com.example.carbigdata.core.service;

import com.example.carbigdata.core.domain.Customer;
import com.example.carbigdata.core.domain.Occurrence;
import com.example.carbigdata.core.domain.OccurrenceStatus;
import com.example.carbigdata.dataprovider.repository.CustomerRepository;
import com.example.carbigdata.dataprovider.repository.OccurrenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccurrenceService {

    private final OccurrenceRepository occurrenceRepository;

    private final CustomerRepository customerRepository;

    public OccurrenceService(OccurrenceRepository occurrenceRepository, CustomerRepository customerRepository) {
        this.occurrenceRepository = occurrenceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Occurrence createOccurrence(Occurrence occurrence) {
        return occurrenceRepository.save(occurrence);
    }

    public Optional<Occurrence> getOccurrence(Long id) {
        return occurrenceRepository.findById(id);
    }

    public List<Occurrence> getAllOccurrences() {
        return occurrenceRepository.findAll();
    }

    public void deleteOccurrence(Long id) {
        occurrenceRepository.deleteById(id);
    }

    public List<Occurrence> getOccurrencesByCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(occurrenceRepository::findByCustomer)
                .orElseGet(List::of);
    }

    public List<Occurrence> getOcorrenciasByStatus(OccurrenceStatus status) {
        return occurrenceRepository.findByStatus(status);
    }
}
