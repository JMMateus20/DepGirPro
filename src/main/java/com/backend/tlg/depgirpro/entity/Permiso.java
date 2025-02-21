package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="rol_id")
    private Rol rol;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "operacion_id")
    private Operacion operacion;

    public Permiso(Rol rol, Operacion operacion) {
        this.rol = rol;
        this.operacion = operacion;
    }
}
