package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface FechaRepository extends JpaRepository<Fecha, Long> {


    Optional<Fecha> findByAnioAndMesAndDia(Integer anio, Integer mes, Integer dia);
}
