package com.example.carbigdata.dataprovider.repository;

import com.example.carbigdata.core.domain.PhotoOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoOccurrenceRepository extends JpaRepository<PhotoOccurrence, Long> {

    List<PhotoOccurrence> findByCodOcorrencia(Long codOcorrencia);
}
