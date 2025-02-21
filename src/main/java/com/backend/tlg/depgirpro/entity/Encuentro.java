package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="encuentros")
public class Encuentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_encuentro")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="id_equipo_local")
    private Equipo equipoLocal;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="id_equipo_visitante")
    private Equipo equipoVisitante;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id_fecha")
    private Fecha fecha;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="id_torneo")
    private Torneo torneo;

    private String jornada;


    public Encuentro(Equipo equipoLocal, Equipo equipoVisitante, Fecha fecha, Torneo torneo, String jornada) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.torneo = torneo;
        this.jornada = jornada;
    }
}
