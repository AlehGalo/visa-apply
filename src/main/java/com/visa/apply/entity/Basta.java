package com.visa.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * JPA Entity representing the d_basta_row table.
 * Contains personal information fields for visa application.
 */
@Entity
@Table(name = "d_basta_row")
@Getter
@Setter
public class Basta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dc_famil", nullable = false)
    private String dcFamil;

    @Column(name = "dc_imena", nullable = false)
    private String dcImena;

    @Column(name = "dc_pol", nullable = false)
    private String dcPol;

    @Column(name = "dc_grad", nullable = false)
    private String dcGrad;

    @Column(name = "dc_ulica", nullable = false)
    private String dcUlica;
}
