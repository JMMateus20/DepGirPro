package com.backend.tlg.depgirpro.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEquipoDTO {

    @NotBlank(message = "no puede estar vacío")
    private String nombre;
    @NotNull(message = "no puede estar vacío")
    @Min(value = 9, message = "debe tener mínimo un valor de 9 jugadores para un equipo")
    @Max(value = 25, message = "debe tener máximo un valor de 25 jugadores para un equipo")
    private Integer cantidad_jugadores;
}
