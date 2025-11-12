package com.visa.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "d_loadosf_entity")
public class Loadosf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "msgheader_id")
    private Msgheader dMsgheader;

    @ManyToOne
    @JoinColumn(name = "lcuz_id")
    private Lcuz dLcuz;

    @ManyToOne
    @JoinColumn(name = "lcdop_id")
    private Lcdop dLcdop;

    @ManyToOne
    @JoinColumn(name = "basta_entity_id")
    private Basta dBasta;

    @ManyToOne
    @JoinColumn(name = "maika_id")
    private Maika dMaika;

    @ManyToOne
    @JoinColumn(name = "sapruga_id")
    private Sapruga dSapruga;

    @ManyToOne
    @JoinColumn(name = "molba_id")
    private Molba dMolba;

    @ManyToOne
    @JoinColumn(name = "domakin_id")
    private Domakin dDomakin;

    @ManyToOne
    @JoinColumn(name = "euroda_id")
    private Euroda dEuroda;

    @ManyToOne
    @JoinColumn(name = "voit_id")
    private Voit dVoit;

    @ManyToOne
    @JoinColumn(name = "images_id")
    private Images dImages;

    @ManyToOne
    @JoinColumn(name = "fingers_id")
    private Fingers dFingers;

    @Column(name = "version")
    private Float version;

}