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
 * JPA Entity representing the d_euroda_row table.
 * Contains European Union information for visa application.
 */
@Entity
@Table(name = "d_euroda_row")
@Getter
@Setter
public class Euroda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eu_famil", nullable = false)
    private String euFamil;
    
    @Column(name = "eu_imena", nullable = false)
    private String euImena;
    
    @Column(name = "eu_nac_bel", nullable = false)
    private String euNacBel;
    
    @Column(name = "eu_rodstvo", nullable = false)
    private String euRodstvo;
}
