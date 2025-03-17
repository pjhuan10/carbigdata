package com.example.carbigdata.api.controller;

import com.example.carbigdata.core.domain.PhotoOccurrence;
import com.example.carbigdata.core.service.PhotoOccurrenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoOccurrenceControllerTest {

    @Mock
    private PhotoOccurrenceService photoOccurrenceService;

    @InjectMocks
    private PhotoOccurrenceController photoOccurrenceController;

    @Test
    void givenValidPhotoOccurrence_whenCreatePhotoOccurrence_thenReturnCreatedPhotoOccurrence() {
        // Given
        PhotoOccurrence photoOccurrence = new PhotoOccurrence(1L, "2025-03-16", "bucket/path/image.jpg", "hash123");
        when(photoOccurrenceService.createPhotoOccurrence(photoOccurrence)).thenReturn(photoOccurrence);

        // When
        ResponseEntity<PhotoOccurrence> response = photoOccurrenceController.createPhotoOccurrence(photoOccurrence);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(photoOccurrence, response.getBody());
        verify(photoOccurrenceService, times(1)).createPhotoOccurrence(photoOccurrence);
    }

    @Test
    void whenGetAllPhotos_thenReturnPhotoOccurrenceList() {
        // Given
        PhotoOccurrence photoOccurrence1 = new PhotoOccurrence(1L, "2025-03-16", "bucket/path/photo1.jpg", "hash123");
        PhotoOccurrence photoOccurrence2 = new PhotoOccurrence(2L, "2025-03-16", "bucket/path/photo2.jpg", "hash124");
        List<PhotoOccurrence> photoOccurrences = List.of(photoOccurrence1, photoOccurrence2);
        when(photoOccurrenceService.getAllPhotos()).thenReturn(photoOccurrences);

        // When
        List<PhotoOccurrence> foundPhotos = photoOccurrenceController.getAllPhotos();

        // Then
        assertNotNull(foundPhotos);
        assertEquals(2, foundPhotos.size());
        assertEquals("photo1.jpg", foundPhotos.get(0).getDescPathBucket());
        assertEquals("photo2.jpg", foundPhotos.get(1).getDescPathBucket());
        verify(photoOccurrenceService, times(1)).getAllPhotos();
    }

    @Test
    void givenCodOcorrencia_whenGetPhotosByOccurrence_thenReturnPhotoOccurrenceList() {
        // Given
        PhotoOccurrence photoOccurrence1 = new PhotoOccurrence(1L, "2025-03-16", "bucket/path/photo1.jpg", "hash123");
        PhotoOccurrence photoOccurrence2 = new PhotoOccurrence(2L, "2025-03-16", "bucket/path/photo2.jpg", "hash124");
        List<PhotoOccurrence> photoOccurrences = List.of(photoOccurrence1, photoOccurrence2);
        when(photoOccurrenceService.getPhotosByOccurrence(123L)).thenReturn(photoOccurrences);

        // When
        List<PhotoOccurrence> foundPhotos = photoOccurrenceController.getPhotosByOccurrence(123L);

        // Then
        assertNotNull(foundPhotos);
        assertEquals(2, foundPhotos.size());
        assertEquals("photo1.jpg", foundPhotos.get(0).getDescPathBucket());
        assertEquals("photo2.jpg", foundPhotos.get(1).getDescPathBucket());
        verify(photoOccurrenceService, times(1)).getPhotosByOccurrence(123L);
    }

    @Test
    void givenPhotoOccurrenceId_whenDeletePhotoOccurrence_thenNoContent() {
        // Given
        doNothing().when(photoOccurrenceService).deletePhotoOccurrence(1L);

        // When
        ResponseEntity<Void> response = photoOccurrenceController.deletePhotoOccurrence(1L);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(photoOccurrenceService, times(1)).deletePhotoOccurrence(1L);
    }
}