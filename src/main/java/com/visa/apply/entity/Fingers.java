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
 * JPA Entity representing the d_fingers_row table.
 * Contains fingerprint information for visa application.
 */
@Entity
@Table(name = "d_fingers_row")
@Getter
@Setter
public class Fingers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fn_datreg", nullable = false)
    private LocalDate fnDatreg;
    
    @Column(name = "fn_datvav", nullable = false)
    private LocalDateTime fnDatvav;
    
    @Column(name = "fn_usera", nullable = false)
    private String fnUsera;
    
    @Column(name = "fn_sid")
    private Integer fnSid;
    
    @Column(name = "fn_pnr")
    private Byte fnPnr;
    
    @Column(name = "fn_vidmol", nullable = false)
    private String fnVidmol;
    
    @Column(name = "fn_drugi", nullable = false)
    private String fnDrugi;
    
    @Column(name = "fn_deviceid")
    private Short fnDeviceid;
    
    @Column(name = "fn_scanres")
    private Short fnScanres;
    
    @Column(name = "fn_width")
    private Short fnWidth;
    
    @Column(name = "fn_height")
    private Short fnHeight;
    
    @Column(name = "fn_pixeldepth")
    private Byte fnPixeldepth;
    
    @Column(name = "fn_compressalgo")
    private Byte fnCompressalgo;
    
    @Column(name = "fn_fingerposition", nullable = false)
    private String fnFingerposition;
    
    @Column(name = "fn_imagequality")
    private Byte fnImagequality;
    
    @Column(name = "fn_image")
    private String fnImage;
    
    @Column(name = "fn_imglen")
    private Integer fnImglen;
    
    @Column(name = "fn_nottakenreason")
    private String fnNottakenreason;
}
