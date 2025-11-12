package com.visa.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * JPA Entity representing the d_lcuz_row table.
 * Contains personal information for visa application.
 */
@Entity
@Table(name = "d_lcuz_row")
@Getter
@Setter
public class Lcuz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vid_zp", nullable = false)
    private String vidZp;
    
    @Column(name = "nac_bel", nullable = false)
    private String nacBel;
    
    @Column(name = "nac_pasp")
    private int nacPasp;
    
    @Column(name = "pasp_val", nullable = false)
    private LocalDate paspVal;
    
    @Column(name = "graj", nullable = false)
    private String graj;
    
    @Column(name = "famil", nullable = false)
    private String famil;
    
    @Column(name = "imena", nullable = false)
    private String imena;
    
    @Column(name = "dat_raj", nullable = false)
    private String datRaj;
    
    @Column(name = "pol", nullable = false)
    private String pol;
    
    @Column(name = "dat_izd", nullable = false)
    private LocalDate datIzd;
}
