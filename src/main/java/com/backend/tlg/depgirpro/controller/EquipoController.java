package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.RegistroEquipoDTO;
import com.backend.tlg.depgirpro.services.EquipoService;
import com.backend.tlg.depgirpro.services.UploadEquipoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;
    private final UploadEquipoService uploadService;

    //admin
    @PostMapping
    public ResponseEntity<?> insertar(@Valid @RequestBody RegistroEquipoDTO dto, BindingResult resultado){
        if(resultado.hasErrors()){
            return ResponseEntity.badRequest().body(resultado.getFieldErrors().stream().map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.equipoService.crearEquipo(dto);
    }

    //jugador y admin
    @GetMapping("/encuentrosLocal/{idEquipo}")
    public ResponseEntity<?> listarEncuentrosDeLocal(@PathVariable Long idEquipo){
        return this.equipoService.listarEncuentrosDeLocal(idEquipo);
    }

    //jugador y admin
    @GetMapping("/encuentrosVisitante/{idEquipo}")
    public ResponseEntity<?> listarEncuentrosDeVisitante(@PathVariable Long idEquipo){
        return this.equipoService.listarEncuentrosDeVisitante(idEquipo);
    }


    @PostMapping("/image/{idEquipo}")
    public ResponseEntity<?> subirImagen(@RequestParam(name = "file", required = true) MultipartFile archivo, @PathVariable Long idEquipo){
        return this.uploadService.upload(archivo, idEquipo);
    }
}
