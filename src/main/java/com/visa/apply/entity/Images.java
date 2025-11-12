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
 * JPA Entity representing the d_images_row table.
 * Contains image information for visa application.
 */
@Entity
@Table(name = "d_images_row")
@Getter
@Setter
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "im_devicetype")
    private Byte imDevicetype;
    
    @Column(name = "im_width")
    private Short imWidth;
    
    @Column(name = "im_height")
    private Short imHeight;
    
    @Column(name = "im_imglen")
    private Short imImglen;
    
    @Column(name = "im_image", nullable = false)
    private String imImage;
}
