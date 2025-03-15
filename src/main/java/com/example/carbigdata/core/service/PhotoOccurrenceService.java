package com.example.carbigdata.core.service;


import com.example.carbigdata.core.domain.PhotoOccurrence;
import com.example.carbigdata.dataprovider.repository.PhotoOccurrenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoOccurrenceService {

    private final PhotoOccurrenceRepository photoOccurrenceRepository;

    public PhotoOccurrenceService(PhotoOccurrenceRepository photoOccurrenceRepository) {
        this.photoOccurrenceRepository = photoOccurrenceRepository;
    }

    public PhotoOccurrence createPhotoOccurrence(PhotoOccurrence photoOccurrence) {
        return photoOccurrenceRepository.save(photoOccurrence);
    }

    public List<PhotoOccurrence> getAllPhotos() {
        return photoOccurrenceRepository.findAll();
    }

    public List<PhotoOccurrence> getPhotosByOccurrence(Long codOcorrencia) {
        return photoOccurrenceRepository.findByCodOcorrencia(codOcorrencia);
    }

    public void deletePhotoOccurrence(Long id) {
        photoOccurrenceRepository.deleteById(id);
    }
}
