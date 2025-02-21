package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.RegistroEquipoDTO;
import org.springframework.http.ResponseEntity;

public interface EquipoService {


    ResponseEntity<?> crearEquipo(RegistroEquipoDTO dto);
    ResponseEntity<?> listarEncuentrosDeLocal(Long idEquipo);
    ResponseEntity<?> listarEncuentrosDeVisitante(Long idEquipo);
}
