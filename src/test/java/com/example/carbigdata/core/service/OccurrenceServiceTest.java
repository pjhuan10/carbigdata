package com.example.carbigdata.core.service;

import com.example.carbigdata.core.domain.Customer;
import com.example.carbigdata.core.domain.Occurrence;
import com.example.carbigdata.core.domain.OccurrenceStatus;
import com.example.carbigdata.dataprovider.repository.CustomerRepository;
import com.example.carbigdata.dataprovider.repository.OccurrenceRepository;
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
class OccurrenceServiceTest {


    @Mock
    private OccurrenceRepository occurrenceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OccurrenceService occurrenceService;

    @Test
    void givenValidOccurrence_whenCreateOccurrence_thenOccurrenceIsSaved() {
        // Given
        Occurrence occurrence = new Occurrence(1L, "Issue with service", OccurrenceStatus.OPEN, null);
        when(occurrenceRepository.save(occurrence)).thenReturn(occurrence);

        // When
        Occurrence createdOccurrence = occurrenceService.createOccurrence(occurrence);

        // Then
        assertNotNull(createdOccurrence);
        assertEquals("Issue with service", createdOccurrence.getDescription());
        verify(occurrenceRepository, times(1)).save(occurrence);
    }

    @Test
    void givenOccurrenceId_whenGetOccurrence_thenReturnOccurrence() {
        // Given
        Occurrence occurrence = new Occurrence(1L, "Issue with service", OccurrenceStatus.OPEN, null);
        when(occurrenceRepository.findById(1L)).thenReturn(Optional.of(occurrence));

        // When
        Optional<Occurrence> foundOccurrence = occurrenceService.getOccurrence(1L);

        // Then
        assertTrue(foundOccurrence.isPresent());
        assertEquals("Issue with service", foundOccurrence.get().getDescription());
        verify(occurrenceRepository, times(1)).findById(1L);
    }

    @Test
    void whenGetAllOccurrences_thenReturnOccurrenceList() {
        // Given
        Occurrence occurrence1 = new Occurrence(1L, "Issue with service", OccurrenceStatus.OPEN, null);
        Occurrence occurrence2 = new Occurrence(2L, "Another issue", OccurrenceStatus.CLOSED, null);
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(occurrenceRepository.findAll()).thenReturn(occurrences);

        // When
        List<Occurrence> foundOccurrences = occurrenceService.getAllOccurrences();

        // Then
        assertEquals(2, foundOccurrences.size());
        assertEquals("Issue with service", foundOccurrences.get(0).getDescription());
        assertEquals("Another issue", foundOccurrences.get(1).getDescription());
        verify(occurrenceRepository, times(1)).findAll();
    }

    @Test
    void givenOccurrenceId_whenDeleteOccurrence_thenVoid() {
        // Given
        doNothing().when(occurrenceRepository).deleteById(1L);

        // When
        occurrenceService.deleteOccurrence(1L);

        // Then
        verify(occurrenceRepository, times(1)).deleteById(1L);
    }

    @Test
    void givenCustomerId_whenGetOccurrencesByCustomer_thenReturnOccurrences() {
        // Given
        Customer customer = new Customer(1L, "John Doe", "123456789");
        Occurrence occurrence1 = new Occurrence(1L, "Issue with service", OccurrenceStatus.OPEN, customer);
        Occurrence occurrence2 = new Occurrence(2L, "Another issue", OccurrenceStatus.CLOSED, customer);
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(occurrenceRepository.findByCustomer(customer)).thenReturn(occurrences);

        // When
        List<Occurrence> foundOccurrences = occurrenceService.getOccurrencesByCustomer(1L);

        // Then
        assertEquals(2, foundOccurrences.size());
        assertEquals("Issue with service", foundOccurrences.get(0).getDescription());
        assertEquals("Another issue", foundOccurrences.get(1).getDescription());
        verify(customerRepository, times(1)).findById(1L);
        verify(occurrenceRepository, times(1)).findByCustomer(customer);
    }

    @Test
    void givenStatus_whenGetOccurrencesByStatus_thenReturnOccurrences() {
        // Given
        Occurrence occurrence1 = new Occurrence(1L, "Issue with service", OccurrenceStatus.OPEN, null);
        Occurrence occurrence2 = new Occurrence(2L, "Another issue", OccurrenceStatus.OPEN, null);
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(occurrenceRepository.findByStatus(OccurrenceStatus.OPEN)).thenReturn(occurrences);

        // When
        List<Occurrence> foundOccurrences = occurrenceService.getOcorrenciasByStatus(OccurrenceStatus.OPEN);

        // Then
        assertEquals(2, foundOccurrences.size());
        assertEquals("Issue with service", foundOccurrences.get(0).getDescription());
        assertEquals("Another issue", foundOccurrences.get(1).getDescription());
        verify(occurrenceRepository, times(1)).findByStatus(OccurrenceStatus.OPEN);
    }

}