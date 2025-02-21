package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.PersonaResponseDTO;
import com.backend.tlg.depgirpro.dto.RegistroPersonaDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import com.backend.tlg.depgirpro.entity.JwtToken;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.entity.Rol;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.mapper.PersonaRequestMapper;
import com.backend.tlg.depgirpro.mapper.PersonaResponseMapper;
import com.backend.tlg.depgirpro.repository.EquipoRepository;
import com.backend.tlg.depgirpro.repository.JwtTokenRepository;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import com.backend.tlg.depgirpro.repository.RolRepository;
import com.backend.tlg.depgirpro.services.MailService;
import com.backend.tlg.depgirpro.services.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRep;
    private final EquipoRepository equipoRep;
    private final PersonaRequestMapper personaRequestMapper;
    private final PersonaResponseMapper personaResponseMapper;
    private final RolRepository rolRep;
    private final JwtTokenRepository tokenRep;
    private final MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> insertar(RegistroPersonaDTO dto) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 2);
        Date expirationRegistryTokenDate = calendar.getTime();
        Map<String, Object> respuesta=new HashMap<>();
        if (this.personaRep.findByCorreo2(dto.getCorreo()).isPresent()){
            respuesta.put("mensaje", "El correo ingresado ya está siendo utilizado por otra cuenta, coloque otra dirección");
            return ResponseEntity.badRequest().body(respuesta);
        }
        Equipo equipoBD=this.equipoRep.findById(dto.getIdEquipo()).orElseThrow(
                ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Equipo no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        Rol rolJugador=this.rolRep.findById(2L).get();
        Persona personaNew=this.personaRequestMapper.toPersona(dto);
        personaNew.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        personaNew.setEquipo(equipoBD);
        personaNew.setRole(rolJugador);
        Persona personaGuardada=this.personaRep.save(personaNew);
        String token = UUID.randomUUID().toString();
        JwtToken tokenCorreo=new JwtToken();
        tokenCorreo.setToken(token);
        tokenCorreo.setPersona(personaGuardada);
        tokenCorreo.setValid(true);
        tokenCorreo.setExpiracion(expirationRegistryTokenDate);
        this.tokenRep.save(tokenCorreo);
        this.mailService.sendVerificationEmail(personaGuardada, token);
        respuesta.put("persona", this.personaResponseMapper.toPersonaResponseDTO(personaNew));
        respuesta.put("mensaje", "Usuario registrado, por favor verifica tu correo para confirmar el registro");
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public boolean verificarUsuario(String token) {
        Optional<JwtToken> tokenBDOptional=this.tokenRep.findByToken(token);
        if(tokenBDOptional.isEmpty() || tokenBDOptional.get().getExpiracion().before(new Date())){
            tokenBDOptional.ifPresent(jwtToken -> {
                this.tokenRep.delete(jwtToken);
                this.personaRep.delete(jwtToken.getPersona());
            });
            return false;
        }
        JwtToken tokenBD=tokenBDOptional.get();
        Persona persona=tokenBD.getPersona();
        persona.setEnabled(true);
        this.personaRep.save(persona);
        tokenBD.setValid(false);
        this.tokenRep.save(tokenBD);
        return true;
    }

}
