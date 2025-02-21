package com.backend.tlg.depgirpro.entity;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="sancion")
public class Sancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sancion")
    private Long id;

    private String sancion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_multa_sancion")
    private Multa multa;


    public Sancion(String sancion, Multa multa) {
        this.sancion = sancion;
        this.multa = multa;
    }
}
