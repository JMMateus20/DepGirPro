package com.backend.tlg.depgirpro.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPersonaDTO {

    @NotBlank(message = "no puede estar vacío")
    private String nombre;
    @NotNull(message = "no puede estar vacío")
    @Max(value = 40, message = "del jugador no debe exceder los 40 años")
    @Min(value = 15, message = "del jugador debe ser de mínimo 15 años")
    private Integer edad;
    @NotNull(message = "no puede estar vacío")
    @Max(value = 100, message = "del jugador no debe exceder los 100 kilos")
    @Min(value = 40, message = "del jugador debe ser de mínimo 40 kilos")
    private Integer peso;
    @NotNull(message = "no puede estar vacío")
    @DecimalMin(value = "1.55", message = "del jugador debe ser de mínimo 1.55 metros")
    @DecimalMax(value = "2.10", message = "del jugador no debe exceder los 2.10 metros")
    private Float altura;
    @NotBlank(message = "no puede estar vacío")
    @Email(message = "debe ser un correo válido")
    private String correo;

    @NotBlank(message = "no puede estar vacío")
    @Size(max = 15, min = 10, message = "debe tener mínimo 10 caracteres y máximo 15")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/~`\\s]).*$", message = "debe contener al menos un caracter especial")
    private String password;
    @NotNull(message = "no puede estar vacío")
    @Max(value = 100, message = "del jugador debe tener un número no mayor a 100")
    @Min(value = 1, message = "del jugador debe tener un número mayor o igual a 1")
    @Positive(message = "debe ser mayor a cero")
    private Integer dorsal;
    @NotNull
    private Long idEquipo;

}
