package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pais")
    private Long id;

    private String pais;
    private String nom;
    private String name;
    private String iso2;
    private String iso3;
    private String phone_code;
    private String predeterminado;

    public Pais(String pais, String nom, String name, String iso2, String iso3, String phone_code, String predeterminado) {
        this.pais = pais;
        this.nom = nom;
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.phone_code = phone_code;
        this.predeterminado = predeterminado;
    }
}
