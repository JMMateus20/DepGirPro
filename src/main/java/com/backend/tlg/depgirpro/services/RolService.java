package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.entity.Permiso;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolService {


    ResponseEntity<PermisoResponseDTO> agregarPermiso(Long idRol, Long idOp);
    ResponseEntity<?> quitarPermiso(Long idRol, Long idPermiso);

    ResponseEntity<List<PermisoResponseDTO>> listarOperacionesPermitidas(Long idRol);


    ResponseEntity<?> eliminarRol(Long idRol);

}
