package com.backend.tlg.depgirpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroFechaDTO {

    @NotNull(message = "no puede estar vacío")
    private Date fecha;

}
