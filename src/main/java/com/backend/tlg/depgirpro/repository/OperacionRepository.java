package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {

    @Query("SELECT o FROM Operacion o WHERE o.permit_all=true")
    List<Operacion> encontrarOperacionesPublicas();
}
