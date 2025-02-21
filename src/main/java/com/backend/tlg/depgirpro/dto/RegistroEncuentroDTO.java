package com.backend.tlg.depgirpro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEncuentroDTO {

    @NotNull(message = "no puede estar vacío")
    private Long idEquipoLocal;
    @NotNull(message = "no puede estar vacío")
    private Long idEquipoVisitante;
    @NotNull(message = "no puede estar vacío")
    private RegistroFechaDTO fecha;

    @NotBlank(message = "no puede estar vacío")
    private String jornada;
}
