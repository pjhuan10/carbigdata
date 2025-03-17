package com.example.carbigdata.core.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "photoOcurrence")
public class PhotoOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPhotoOcurrence;

    @ManyToOne
    @JoinColumn(name = "cod_ocurrence")
    private Occurrence codOcurrence;

    @NotNull
    private String dtaCreation;
    @NotNull
    private String descPathBucket;
    @NotNull
    private String descHash;


    public PhotoOccurrence(String date, String s, String hash123) {
    }

    public PhotoOccurrence(long l, String date, String s, String hash124) {
    }
}
