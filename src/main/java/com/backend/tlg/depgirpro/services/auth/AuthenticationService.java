package com.backend.tlg.depgirpro.services.auth;

import com.backend.tlg.depgirpro.dto.LoginRequestDTO;
import com.backend.tlg.depgirpro.dto.LoginResponseDTO;
import com.backend.tlg.depgirpro.dto.PerfilResponseDTO;
import com.backend.tlg.depgirpro.entity.JwtToken;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.exceptions.BadCredentialsExceptionManaged;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.exceptions.UserDisabledExceptionManaged;
import com.backend.tlg.depgirpro.repository.JwtTokenRepository;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PersonaRepository personaRep;
    @Autowired
    private JwtTokenRepository jwtTokenRep;


    public LoginResponseDTO login(LoginRequestDTO credenciales){
        UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(credenciales.getCorreo(), credenciales.getPassword());
        try{
            authManager.authenticate(upat);
        }catch(BadCredentialsException e){
            throw new BadCredentialsExceptionManaged("400", "Error de validación", "Correo o contraseña incorrectos", HttpStatus.BAD_REQUEST);
        }catch (DisabledException e){
            throw new UserDisabledExceptionManaged("403", "Error de validación", "Su cuenta está inhabilitada, por favor, verifica tu correo para validar tu registro o contacta con el administrador.", HttpStatus.FORBIDDEN);
        }

        Persona persona=this.personaRep.findByCorreo(credenciales.getCorreo()).orElseThrow(
                ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Persona no encontrada en la base de datos", HttpStatus.NOT_FOUND)
        );
        String jwt=this.jwtService.generarToken(persona, generarClaims(persona));
        this.guardarToken(jwt, persona);
        return new LoginResponseDTO(jwt);

    }

    public void logout(HttpServletRequest req){
        String jwt=this.jwtService.extractTokenFromRequest(req);
        if (jwt==null || !StringUtils.hasText(jwt)){
            return;
        }
        Optional<JwtToken> token=this.jwtTokenRep.findByToken(jwt);
        if (token.isPresent() && token.get().isValid()){
            JwtToken tokenBD=token.get();
            tokenBD.setValid(false);
            this.jwtTokenRep.save(tokenBD);

        }
    }

    private Map<String, Object> generarClaims(Persona persona){
        Map<String, Object> claims=new HashMap<>();
        claims.put("id", persona.getId());
        claims.put("nombre",persona.getNombre());
        claims.put("equipo", persona.getEquipo()!=null?persona.getEquipo().getId():"Es administrador, no tiene equipo.");
        claims.put("permisos", persona.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return claims;

    }

    public boolean validateToken(String jwt){
        try{
            this.jwtService.extractUsername(jwt);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public PerfilResponseDTO findLoggedUser(){
        Persona personaBD=personaRep.findByCorreo((String) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).get();

        return new PerfilResponseDTO(
                personaBD.getId(),
                personaBD.getNombre(),
                personaBD.getCorreo(),
                personaBD.getEquipo()!=null?personaBD.getEquipo().getNombre():"Sin equipo",
                personaBD.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    private void guardarToken(String jwt, Persona persona){
        this.jwtTokenRep.save(new JwtToken(jwt, this.jwtService.extractExpiration(jwt), persona));
    }

}
