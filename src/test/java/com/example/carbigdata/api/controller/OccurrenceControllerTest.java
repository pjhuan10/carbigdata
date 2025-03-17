package com.example.carbigdata.api.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.carbigdata.core.domain.Occurrence;
import com.example.carbigdata.core.domain.OccurrenceStatus;
import com.example.carbigdata.core.service.OccurrenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OccurrenceControllerTest {

    @Mock
    private OccurrenceService occurrenceService;

    @InjectMocks
    private OccurrenceController occurrenceController;

    @Test
    void givenValidOccurrence_whenCreateOccurrence_thenReturnCreatedOccurrence() {
        // Given
        Occurrence occurrence = new Occurrence(1L, OccurrenceStatus.OPEN, "Description");
        when(occurrenceService.createOccurrence(occurrence)).thenReturn(occurrence);

        // When
        ResponseEntity<Occurrence> response = occurrenceController.createOccurrence(occurrence);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(occurrence, response.getBody());
        verify(occurrenceService, times(1)).createOccurrence(occurrence);
    }

    @Test
    void givenExistingId_whenGetOccurrence_thenReturnOccurrence() {
        // Given
        Occurrence occurrence = new Occurrence(1L, OccurrenceStatus.OPEN, "Description");
        when(occurrenceService.getOccurrence(1L)).thenReturn(Optional.of(occurrence));

        // When
        ResponseEntity<Occurrence> response = occurrenceController.getOccurrence(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(occurrence, response.getBody());
        verify(occurrenceService, times(1)).getOccurrence(1L);
    }

    @Test
    void givenNonExistingId_whenGetOccurrence_thenReturnNotFound() {
        // Given
        when(occurrenceService.getOccurrence(1L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Occurrence> response = occurrenceController.getOccurrence(1L);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(occurrenceService, times(1)).getOccurrence(1L);
    }

    @Test
    void whenGetAllOccurrences_thenReturnOccurrenceList() {
        // Given
        Occurrence occurrence1 = new Occurrence(1L, OccurrenceStatus.OPEN, "Description 1");
        Occurrence occurrence2 = new Occurrence(2L, OccurrenceStatus.CLOSED, "Description 2");
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(occurrenceService.getAllOccurrences()).thenReturn(occurrences);

        // When
        List<Occurrence> result = occurrenceController.getAllOccurrences();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(occurrence1, result.get(0));
        assertEquals(occurrence2, result.get(1));
        verify(occurrenceService, times(1)).getAllOccurrences();
    }

    @Test
    void givenCustomerId_whenGetOccurrencesByCustomer_thenReturnOccurrenceList() {
        // Given
        Occurrence occurrence1 = new Occurrence(1L, OccurrenceStatus.OPEN, "Description 1");
        Occurrence occurrence2 = new Occurrence(2L, OccurrenceStatus.CLOSED, "Description 2");
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(occurrenceService.getOccurrencesByCustomer(123L)).thenReturn(occurrences);

        // When
        List<Occurrence> result = occurrenceController.getOccurrencesByCustomer(123L);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(occurrence1, result.get(0));
        assertEquals(occurrence2, result.get(1));
        verify(occurrenceService, times(1)).getOccurrencesByCustomer(123L);
    }

    @Test
    void givenStatus_whenGetOccurrencesByStatus_thenReturnOccurrenceList() {
        // Given
        Occurrence occurrence1 = new Occurrence(1L, OccurrenceStatus.OPEN, "Description 1");
        Occurrence occurrence2 = new Occurrence(2L, OccurrenceStatus.OPEN, "Description 2");
        List<Occurrence> occurrences = List.of(occurrence1, occurrence2);
        when(occurrenceService.getOcorrenciasByStatus(OccurrenceStatus.OPEN)).thenReturn(occurrences);

        // When
        List<Occurrence> result = occurrenceController.getOccurrencesByStatus(OccurrenceStatus.OPEN);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(occurrence1, result.get(0));
        assertEquals(occurrence2, result.get(1));
        verify(occurrenceService, times(1)).getOcorrenciasByStatus(OccurrenceStatus.OPEN);
    }

    @Test
    void givenOccurrenceId_whenDeleteOccurrence_thenNoContent() {
        // Given
        doNothing().when(occurrenceService).deleteOccurrence(1L);

        // When
        ResponseEntity<Void> response = occurrenceController.deleteOccurrence(1L);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(occurrenceService, times(1)).deleteOccurrence(1L);
    }
}