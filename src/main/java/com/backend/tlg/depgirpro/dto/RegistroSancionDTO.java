package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroSancionDTO {


    private String sancion;
    private RegistroMultaDTO multaDTO;
}
