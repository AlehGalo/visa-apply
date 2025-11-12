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
 * JPA Entity representing the d_lcdop_row table.
 * Contains additional personal information for visa application.
 */
@Entity
@Table(name = "d_lcdop_row")
@Getter
@Setter
public class Lcdop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ld_mrjdarj", nullable = false)
    private String ldMrjdarj;
    
    @Column(name = "ld_mrjnm", nullable = false)
    private String ldMrjnm;
    
    @Column(name = "ld_mrjgraj", nullable = false)
    private String ldMrjgraj;
    
    @Column(name = "ld_zenen", nullable = false)
    private String ldZenen;
    
    @Column(name = "ld_jit_darj", nullable = false)
    private String ldJitDarj;
    
    @Column(name = "ld_jit_nm", nullable = false)
    private String ldJitNm;
    
    @Column(name = "ld_jit_ul", nullable = false)
    private String ldJitUl;
    
    @Column(name = "ld_tel")
    private Long ldTel;
    
    @Column(name = "ld_rabota", nullable = false)
    private String ldRabota;
    
    @Column(name = "ld_profkod", nullable = false)
    private String ldProfkod;
    
    @Column(name = "ld_profesia", nullable = false)
    private String ldProfesia;
    
    @Column(name = "ld_sl_darj", nullable = false)
    private String ldSlDarj;
    
    @Column(name = "ld_sl_nm", nullable = false)
    private String ldSlNm;
    
    @Column(name = "ld_sl_ul", nullable = false)
    private String ldSlUl;
}
