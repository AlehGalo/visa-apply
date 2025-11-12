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
 * JPA Entity representing the d_voit_row table.
 * Contains voter information for visa application.
 */
@Entity
@Table(name = "d_voit_row")
@Getter
@Setter
public class Voit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vnom", nullable = false)
    private String vnom;

    @Column(name = "vime", nullable = false)
    private String vime;

    @Column(name = "bgime", nullable = false)
    private String bgime;

    @Column(name = "bgadres", nullable = false)
    private String bgadres;
}
