package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroTorneoDTO {

    private RegistroFechaDTO fechaInicial;
    private RegistroFechaDTO fechaFinal;

}
