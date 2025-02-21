package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.PerfilResponseDTO;
import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.services.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    //ADMIN
    @PostMapping("/permiso/{idRol}")
    public ResponseEntity<PermisoResponseDTO> agregarPermiso(@PathVariable Long idRol, @RequestParam(name = "operacion") Long idOp){
        return this.rolService.agregarPermiso(idRol, idOp);
    }

    //admin
    @DeleteMapping("/permiso/{idRol}")
    public ResponseEntity<?> quitarPermiso(@PathVariable Long idRol, @RequestParam(name = "permiso") Long idPermiso){
        return this.rolService.quitarPermiso(idRol, idPermiso);
    }

    //admin
    @GetMapping("/permiso/{idRol}")
    public ResponseEntity<List<PermisoResponseDTO>> listarOperacionesPermitidas(@PathVariable Long idRol){
        return this.rolService.listarOperacionesPermitidas(idRol);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long idRol){
        return this.rolService.eliminarRol(idRol);
    }
}
