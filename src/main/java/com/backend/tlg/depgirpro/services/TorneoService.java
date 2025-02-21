package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.RegistroEncuentroDTO;
import com.backend.tlg.depgirpro.dto.RegistroTorneoDTO;
import org.springframework.http.ResponseEntity;

public interface TorneoService {

    ResponseEntity<?> insertar(RegistroTorneoDTO dto);

    ResponseEntity<?> agregarEncuentro(Long idTorneo, RegistroEncuentroDTO dto);
}
