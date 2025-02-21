package com.backend.tlg.depgirpro.config.security.filter;

import com.backend.tlg.depgirpro.entity.JwtToken;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.JwtTokenRepository;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import com.backend.tlg.depgirpro.services.auth.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PersonaRepository personaRep;
    @Autowired
    private JwtTokenRepository tokenRep;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=this.jwtService.extractTokenFromRequest(request);
        if (jwt==null || !StringUtils.hasText(jwt)){
            filterChain.doFilter(request, response);
            return;
        }
        Optional<JwtToken> tokenBD=this.tokenRep.findByToken(jwt);
        boolean isValid=this.validateToken(tokenBD);
        if (!isValid){
            filterChain.doFilter(request, response);
            return;
        }
        String correo=this.jwtService.extractUsername(jwt);
        Persona personaBD=this.personaRep.findByCorreo(correo).orElseThrow(()->
                new NotFoundExceptionManaged("404", "Error de búsqueda", "Persona no encontrada en la base de datos", HttpStatus.NOT_FOUND));
        UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken
                (correo, null, personaBD.getAuthorities());
        upat.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upat);
        filterChain.doFilter(request, response);

    }


    private boolean validateToken(Optional<JwtToken> token){
        if (!token.isPresent()){
            return false;
        }
        JwtToken tokenBD=token.get();
        Date now=new Date(System.currentTimeMillis());
        boolean isValid=tokenBD.isValid() && tokenBD.getExpiracion().after(now);
        if(!isValid){
            updateTokenStatus(tokenBD);
        }
        return isValid;
    }


    private void updateTokenStatus(JwtToken tokenBD){
        tokenBD.setValid(false);
        this.tokenRep.save(tokenBD);
    }
}
