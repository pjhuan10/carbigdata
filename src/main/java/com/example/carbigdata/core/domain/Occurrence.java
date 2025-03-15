package com.example.carbigdata.core.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "occurrence")
public class Occurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String description;

    private LocalDate occurrenceDate;

    @Enumerated(EnumType.STRING)
    private OccurrenceStatus status;

}
