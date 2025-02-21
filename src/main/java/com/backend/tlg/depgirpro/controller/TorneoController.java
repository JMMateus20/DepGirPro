package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.RegistroEncuentroDTO;
import com.backend.tlg.depgirpro.dto.RegistroTorneoDTO;
import com.backend.tlg.depgirpro.services.TorneoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    //admin
    @PostMapping
    public ResponseEntity<?> insertar(@Valid @RequestBody RegistroTorneoDTO dto, BindingResult resultado){
        if (resultado.hasErrors()){
            return ResponseEntity.badRequest().body(resultado.getFieldErrors().stream().map(err->"el campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.torneoService.insertar(dto);
    }

    //admin
    @PostMapping("/encuentros/{idTorneo}")
    public ResponseEntity<?> agregarEncuentro(@PathVariable Long idTorneo,@Valid @RequestBody RegistroEncuentroDTO dto, BindingResult resultado){
        if (resultado.hasErrors()){
            return ResponseEntity.badRequest().body(resultado.getFieldErrors().stream().map(err->"el campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.torneoService.agregarEncuentro(idTorneo, dto);
    }
}
