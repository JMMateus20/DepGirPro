package com.backend.tlg.depgirpro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message="no puede estar vacío")
    private String correo;
    @NotBlank(message="no puede estar vacío")
    private String password;
}
