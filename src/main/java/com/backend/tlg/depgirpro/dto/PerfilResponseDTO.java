package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String equipo;
    private List<String> rol=new ArrayList<>();

}
