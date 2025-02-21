package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import com.backend.tlg.depgirpro.dto.ImagenPersonaResponseDTO;
import com.backend.tlg.depgirpro.services.UploadEquipoService;
import com.backend.tlg.depgirpro.services.UploadPersonaService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/images")
public class ImagenController {


    private final UploadEquipoService uploadEquipoService;
    private final UploadPersonaService uploadPersonaService;

    @PutMapping("/update/{idImagen}")
    public ResponseEntity<ImagenEquipoResponseDTO> cambiarImagenEquipo(@RequestParam(name = "file", required = true) MultipartFile archivo, @PathVariable Long idImagen){
        return this.uploadEquipoService.cambiarFoto(archivo, idImagen);
    }

    @GetMapping("/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFotoEquipo(@PathVariable String nombreFoto){
        return this.uploadEquipoService.verFoto(nombreFoto);
    }

    @GetMapping("/personas/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFotoPersona(@PathVariable String nombreFoto){
        return this.uploadPersonaService.verFoto(nombreFoto);
    }

    @PutMapping("/update/personas/{idPersona}")
    public ResponseEntity<ImagenPersonaResponseDTO> cambiarImagenPersona(@RequestParam(name = "file", required = true) MultipartFile archivo, @PathVariable Long idPersona){
        return this.uploadPersonaService.cambiarFoto(archivo, idPersona);
    }



}
