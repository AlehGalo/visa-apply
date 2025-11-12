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
import java.time.LocalDateTime;


/**
 * JPA Entity representing the molba_row table.
 * Contains visa application information.
 */
@Entity
@Table(name = "molba_row")
@Setter
@Getter
public class Molba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dat_vli", nullable = false)
    private LocalDate datVli;
    
    @Column(name = "dat_izl", nullable = false)
    private LocalDate datIzl;
    
    @Column(name = "vidvis", nullable = false)
    private String vidvis;
    
    @Column(name = "brvl")
    private byte brvl;
    
    @Column(name = "vidus", nullable = false)
    private String vidus;
    
    @Column(name = "valvis", nullable = false)
    private String valvis;
    
    @Column(name = "brdni")
    private byte brdni;
    
    @Column(name = "cel")
    private byte cel;
    
    @Column(name = "mol_dat_vav", nullable = false)
    private LocalDateTime molDatVav;
    
    @Column(name = "gratis", nullable = false)
    private String gratis;
    
    @Column(name = "imavisa", nullable = false)
    private String imavisa;
    
    @Column(name = "cenamol")
    private byte cenamol;
    
    @Column(name = "cenacurr", nullable = false)
    private String cenacurr;
    
    @Column(name = "maindest", nullable = false)
    private String maindest;
    
    @Column(name = "maindestnm", nullable = false)
    private String maindestnm;
    
    @Column(name = "gkpp_darj", nullable = false)
    private String gkppDarj;
    
    @Column(name = "gkpp_text", nullable = false)
    private String gkppText;
    
    @Column(name = "text_ini", nullable = false)
    private String textIni;
}
