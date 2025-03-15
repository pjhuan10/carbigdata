package com.example.carbigdata.core.domain;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "photoOcurrence")
public class PhotoOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPhotoOcurrence;

    @ManyToOne
    @JoinColumn(name = "cod_ocurrence")
    private Occurrence codOcurrence;

    private String dtaCreation;
    private String descPathBucket;
    private String descHash;
}
