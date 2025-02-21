package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="imagenes_equipo")
public class ImagenEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="equipo_id")
    private Equipo equipo;


    public ImagenEquipo(String url, Equipo equipo) {
        this.url = url;
        this.equipo = equipo;
    }
}
