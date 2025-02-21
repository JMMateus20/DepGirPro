package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="operaciones")
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String metodo_http;

    private boolean permit_all;

    @ManyToOne(optional = false)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;
}
