package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.RegistroSancionDTO;
import org.springframework.http.ResponseEntity;

public interface SancionService {

    ResponseEntity<?> insertar(RegistroSancionDTO dto);
}
