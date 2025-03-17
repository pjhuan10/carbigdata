package com.example.carbigdata.core.service;


import com.example.carbigdata.core.domain.PhotoOccurrence;
import com.example.carbigdata.dataprovider.repository.PhotoOccurrenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoOccurrenceServiceTest {

    @Mock
    private PhotoOccurrenceRepository photoOccurrenceRepository;

    @InjectMocks
    private PhotoOccurrenceService photoOccurrenceService;

    @Test
    void givenValidPhotoOccurrence_whenCreatePhotoOccurrence_thenPhotoOccurrenceIsSaved() {
        // Given
        PhotoOccurrence photoOccurrence = new PhotoOccurrence("2025-03-16", "bucket/path/image.jpg", "hash123");
        when(photoOccurrenceRepository.save(photoOccurrence)).thenReturn(photoOccurrence);

        // When
        PhotoOccurrence createdPhotoOccurrence = photoOccurrenceService.createPhotoOccurrence(photoOccurrence);

        // Then
        assertNotNull(createdPhotoOccurrence);
        assertEquals("2025-03-16", createdPhotoOccurrence.getDtaCreation());
        assertEquals("bucket/path/image.jpg", createdPhotoOccurrence.getDescPathBucket());
        assertEquals("hash123", createdPhotoOccurrence.getDescHash());
        verify(photoOccurrenceRepository, times(1)).save(photoOccurrence);
    }

    @Test
    void whenGetAllPhotos_thenReturnPhotoOccurrenceList() {
        // Given
        PhotoOccurrence photoOccurrence1 = new PhotoOccurrence(1L, "2025-03-16", "bucket/photo1.jpg", "hash123");
        PhotoOccurrence photoOccurrence2 = new PhotoOccurrence(2L, "2025-03-16", "bucket/photo2.jpg", "hash124");
        List<PhotoOccurrence> photoOccurrences = List.of(photoOccurrence1, photoOccurrence2);
        when(photoOccurrenceRepository.findAll()).thenReturn(photoOccurrences);

        // When
        List<PhotoOccurrence> foundPhotos = photoOccurrenceService.getAllPhotos();

        // Then
        assertEquals(2, foundPhotos.size());
        assertEquals("bucket/photo1.jpg", foundPhotos.get(0).getDescPathBucket());
        assertEquals("bucket/photo2.jpg", foundPhotos.get(1).getDescPathBucket());
        verify(photoOccurrenceRepository, times(1)).findAll();
    }

    @Test
    void givenCodOcorrencia_whenGetPhotosByOccurrence_thenReturnPhotoOccurrenceList() {
        // Given
        PhotoOccurrence photoOccurrence1 = new PhotoOccurrence(1L, "2025-03-16", "bucket/photo1.jpg", "hash123");
        PhotoOccurrence photoOccurrence2 = new PhotoOccurrence(2L, "2025-03-16", "bucket/photo2.jpg", "hash124");
        List<PhotoOccurrence> photoOccurrences = List.of(photoOccurrence1, photoOccurrence2);
        when(photoOccurrenceRepository.findByCodOcorrencia(123L)).thenReturn(photoOccurrences);

        // When
        List<PhotoOccurrence> foundPhotos = photoOccurrenceService.getPhotosByOccurrence(123L);

        // Then
        assertEquals(2, foundPhotos.size());
        assertEquals("bucket/photo1.jpg", foundPhotos.get(0).getDescPathBucket());
        assertEquals("bucket/photo2.jpg", foundPhotos.get(1).getDescPathBucket());
        verify(photoOccurrenceRepository, times(1)).findByCodOcorrencia(123L);
    }

    @Test
    void givenPhotoOccurrenceId_whenDeletePhotoOccurrence_thenVoid() {
        // Given
        doNothing().when(photoOccurrenceRepository).deleteById(1L);

        // When
        photoOccurrenceService.deletePhotoOccurrence(1L);

        // Then
        verify(photoOccurrenceRepository, times(1)).deleteById(1L);
    }
}