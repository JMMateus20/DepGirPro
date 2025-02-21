package com.backend.tlg.depgirpro.config.security;

import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInjector {
    @Autowired
    private AuthenticationConfiguration authConfig;
    @Autowired
    private PersonaRepository personaRep;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return this.authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return (correo)->{
            return this.personaRep.findByCorreo(correo).orElseThrow(
                    ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Usuario no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        };
    }

    @Bean
    public BouncyCastleProvider bouncyCastleProvider(){
        return new BouncyCastleProvider();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }


}
