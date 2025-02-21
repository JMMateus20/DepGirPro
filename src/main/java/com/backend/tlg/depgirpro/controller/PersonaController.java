package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.RegistroPersonaDTO;
import com.backend.tlg.depgirpro.services.PersonaService;
import com.backend.tlg.depgirpro.services.UploadPersonaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;
    private final UploadPersonaService uploadPersonaService;

    @PostMapping("/registro")
    public ResponseEntity<?> insertar(@Valid @RequestBody RegistroPersonaDTO dto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldErrors().stream().map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.personaService.insertar(dto);

    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam String token) {
        if (this.personaService.verificarUsuario(token)) {
            return "Cuenta verificada con éxito";
        } else {
            return "El correo de verificación es inválido o ha expirado";
        }
    }

    @PostMapping("/upload/{idPersona}")
    public ResponseEntity<?> cargarFoto(@RequestParam(name = "file", required = true) MultipartFile archivo, @PathVariable Long idPersona){
        return this.uploadPersonaService.upload(archivo, idPersona);
    }
}
