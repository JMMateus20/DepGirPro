package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneoResponseDTO {

    private Long id;
    private Date fechaInicial;
    private Date fechaFinal;
}
