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
 * JPA Entity representing the d_msgheader_row table.
 * Contains message header information for visa application.
 */
@Entity
@Table(name = "d_msgheader_row")
@Setter
@Getter
public class Msgheader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mh_kscreated", nullable = false)
    private String mhKscreated;
    
    @Column(name = "mh_regnom")
    private int mhRegnom;
    
    @Column(name = "mh_vfsrefno", nullable = false)
    private String mhVfsrefno;
    
    @Column(name = "mh_usera", nullable = false)
    private String mhUsera;
    
    @Column(name = "mh_datvav", nullable = false)
    private String mhDatvav;
}
