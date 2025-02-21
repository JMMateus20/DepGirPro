package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="torneos")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_torneo")
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_fecha_inicial")
    private Fecha fechaInicial;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_fecha_final")
    private Fecha fechaFinal;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "torneo")
    private List<Encuentro> encuentros=new ArrayList<>();

    public Torneo(Fecha fechaInicial, Fecha fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;

    }
}
