package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponseDTO {

    private Long idPersona;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private Float altura;
    private String correo;
    private Integer dorsal;
    private String nombreEquipo;
}
