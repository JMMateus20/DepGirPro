package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncuentrosPorEquipoDTO {

    private Long idToneo;
    private String nombreEquipo;
    private Date fecha;
    private String jornada;

    private Date fechaInicialTorneo;
    private Date fechaFinalTorneo;
}
