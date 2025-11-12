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
 * JPA Entity representing the d_domakin_row table.
 * Contains household information for visa application.
 */
@Entity
@Table(name = "d_domakin_row")
@Getter
@Setter
public class Domakin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dm_vid", nullable = false)
    private String dmVid;
    
    @Column(name = "nom_pok", nullable = false)
    private String nomPok;
    
    @Column(name = "dom_graj", nullable = false)
    private String domGraj;
    
    @Column(name = "dom_famil", nullable = false)
    private String domFamil;
    
    @Column(name = "dom_ime", nullable = false)
    private String domIme;
    
    @Column(name = "dom_darj", nullable = false)
    private String domDarj;
    
    @Column(name = "dom_nm")
    private Short domNm;
    
    @Column(name = "dom_adres", nullable = false)
    private String domAdres;
    
    @Column(name = "ved_darj", nullable = false)
    private String vedDarj;
    
    @Column(name = "ved_nm", nullable = false)
    private String vedNm;
}
