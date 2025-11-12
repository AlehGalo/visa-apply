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
 * JPA Entity representing the d_sapruga_row table.
 * Contains spouse information for visa application.
 */
@Entity
@Table(name = "d_sapruga_row")
@Getter
@Setter
public class Sapruga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sp_mrjdarj", nullable = false)
    private String spMrjdarj;
}
