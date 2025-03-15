package com.example.carbigdata.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Entity
@Builder
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

