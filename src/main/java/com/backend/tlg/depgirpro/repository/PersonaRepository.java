package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {


    Optional<Persona> findByCorreo(String correo);

    @Query("SELECT p FROM Persona p WHERE p.correo=:correo AND p.enabled=true")
    Optional<Persona> findByCorreo2(@Param("correo") String correo);
}
