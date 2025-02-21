package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisoResponseDTO {


    private Long idPermiso;

    private Long idRol;
    private String rol;
    private Long idOperacion;
    private String operacion;
}
