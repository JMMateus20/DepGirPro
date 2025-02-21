package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_equipo")
    private Long id;
    private String nombre;
    private Integer cantidad_jugadores;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "equipo")
    private List<ImagenEquipo> imagenes=new ArrayList<>();


    public Equipo(String nombre, Integer cantidad_jugadores) {
        this.nombre = nombre;
        this.cantidad_jugadores = cantidad_jugadores;
    }
}
