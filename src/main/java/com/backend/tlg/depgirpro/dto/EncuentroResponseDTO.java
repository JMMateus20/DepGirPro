package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncuentroResponseDTO {

    private Long id;
    private String nomEquipoLocal;
    private String nomEquipoVisitante;
    private Date fecha;
    private String jornada;
}
