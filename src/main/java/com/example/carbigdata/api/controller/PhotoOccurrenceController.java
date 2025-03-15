package com.example.carbigdata.api.controller;


import com.example.carbigdata.core.domain.PhotoOccurrence;
import com.example.carbigdata.core.service.PhotoOccurrenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photo-occurrences")
public class PhotoOccurrenceController {

    private final PhotoOccurrenceService photoOccurrenceService;

    public PhotoOccurrenceController(PhotoOccurrenceService photoOccurrenceService) {
        this.photoOccurrenceService = photoOccurrenceService;
    }

    @PostMapping
    public ResponseEntity<PhotoOccurrence> createPhotoOccurrence(@RequestBody PhotoOccurrence photoOccurrence) {
        PhotoOccurrence createdPhoto = photoOccurrenceService.createPhotoOccurrence(photoOccurrence);
        return ResponseEntity.ok(createdPhoto);
    }

    @GetMapping
    public List<PhotoOccurrence> getAllPhotos() {
        return photoOccurrenceService.getAllPhotos();
    }

    @GetMapping("/occurrence/{cod_ocorrencia}")
    public List<PhotoOccurrence> getPhotosByOccurrence(@PathVariable Long cod_ocorrencia) {
        return photoOccurrenceService.getPhotosByOccurrence(cod_ocorrencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoOccurrence(@PathVariable Long id) {
        photoOccurrenceService.deletePhotoOccurrence(id);
        return ResponseEntity.noContent().build();
    }
}
