package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.RegistroSancionDTO;
import com.backend.tlg.depgirpro.services.SancionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/sanciones")
public class SancionController {

    private final SancionService sancionService;

    //admin
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody RegistroSancionDTO dto){
        return this.sancionService.insertar(dto);
    }
}
