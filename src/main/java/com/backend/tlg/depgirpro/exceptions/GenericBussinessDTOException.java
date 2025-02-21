package com.backend.tlg.depgirpro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericBussinessDTOException {

    private String titulo;
    private String code;

    private String detalle;

    private String tipo;



}
