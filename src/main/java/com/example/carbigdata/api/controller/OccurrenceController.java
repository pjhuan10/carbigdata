package com.example.carbigdata.api.controller;

import com.example.carbigdata.core.domain.Occurrence;
import com.example.carbigdata.core.domain.OccurrenceStatus;
import com.example.carbigdata.core.service.OccurrenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {

    private final OccurrenceService occurrenceService;

    public OccurrenceController(OccurrenceService occurrenceService) {
        this.occurrenceService = occurrenceService;
    }

    @PostMapping
    public ResponseEntity<Occurrence> createOccurrence(@RequestBody Occurrence occurrence) {
        Occurrence createdOccurrence = occurrenceService.createOccurrence(occurrence);
        return ResponseEntity.ok(createdOccurrence);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Occurrence> getOccurrence(@PathVariable Long id) {
        Optional<Occurrence> occurrence = occurrenceService.getOccurrence(id);
        return occurrence.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Occurrence> getAllOccurrences() {
        return occurrenceService.getAllOccurrences();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOccurrence(@PathVariable Long id) {
        occurrenceService.deleteOccurrence(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public List<Occurrence> getOccurrencesByCustomer(@PathVariable Long customerId) {
        return occurrenceService.getOccurrencesByCustomer(customerId);
    }

    @GetMapping("/status/{status}")
    public List<Occurrence> getOccurrencesByStatus(@PathVariable OccurrenceStatus status) {
        return occurrenceService.getOcorrenciasByStatus(status);
    }
}
