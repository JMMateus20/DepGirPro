package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.LoginRequestDTO;
import com.backend.tlg.depgirpro.dto.LoginResponseDTO;
import com.backend.tlg.depgirpro.dto.LogoutResponseDTO;
import com.backend.tlg.depgirpro.dto.PerfilResponseDTO;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.services.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService authService;



    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO credenciales, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldErrors().stream().map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(this.authService.login(credenciales));

    }


    @GetMapping
    public ResponseEntity<Boolean> isTokenValid(@RequestParam String jwt){
        return ResponseEntity.ok(this.authService.validateToken(jwt));
    }


    @GetMapping("/profile")
    public ResponseEntity<PerfilResponseDTO> obtenerUsuarioLogeado(){
        return ResponseEntity.ok(this.authService.findLoggedUser());
    }


    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(HttpServletRequest req){
        this.authService.logout(req);
        return ResponseEntity.ok(new LogoutResponseDTO("Logout exitoso"));
    }
}
