package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="multa_sancion")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_multa_sancion")
    private Long id;

    private Float multa;

    public Multa(Float multa) {
        this.multa = multa;
    }
}
