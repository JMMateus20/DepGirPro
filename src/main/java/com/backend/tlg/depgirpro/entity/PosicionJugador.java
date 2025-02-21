package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="posicion_jugador")
public class PosicionJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_posicion")
    private Long id;

    private String nombre;


    private String abreviatura;

    public PosicionJugador(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }
}
