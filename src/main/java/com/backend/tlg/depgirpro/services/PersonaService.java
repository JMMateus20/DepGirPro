package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.RegistroPersonaDTO;
import org.springframework.http.ResponseEntity;

public interface PersonaService {

    ResponseEntity<?> insertar(RegistroPersonaDTO dto);
    public boolean verificarUsuario(String token);
}
