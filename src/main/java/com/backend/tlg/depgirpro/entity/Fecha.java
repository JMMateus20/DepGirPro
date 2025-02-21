package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name="fecha")
public class Fecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idfecha")
    private Long id;

    private Date fecha;
    private Integer anio;
    private Integer mes;
    private Integer dia;


    public Fecha(Date fecha, Integer anio, Integer mes, Integer dia) {
        this.fecha = fecha;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
    }
}
