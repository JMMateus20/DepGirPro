package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.dto.EncuentrosPorEquipoDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("SELECT new com.backend.tlg.depgirpro.dto.EncuentrosPorEquipoDTO(t.id, e.nombre, f.fecha, p.jornada, f1.fecha, f2.fecha) " +
    " FROM Encuentro p INNER JOIN p.torneo t INNER JOIN p.equipoLocal e INNER JOIN p.torneo.fechaInicial f1 INNER JOIN p.torneo.fechaFinal f2 INNER JOIN p.fecha f WHERE p.equipoLocal.id=:idEquipo ORDER BY f.fecha ASC")
    List<EncuentrosPorEquipoDTO> findEncuentrosDeLocal(@Param("idEquipo") Long idEquipo);


    @Query("SELECT new com.backend.tlg.depgirpro.dto.EncuentrosPorEquipoDTO(t.id, p.nombre, e.fecha.fecha, e.jornada, t.fechaInicial.fecha, t.fechaFinal.fecha) " +
    " FROM Torneo t INNER JOIN t.encuentros e INNER JOIN e.equipoVisitante p WHERE p.id=:idEquipo ORDER BY e.fecha.fecha ASC")
    List<EncuentrosPorEquipoDTO> findEncuentrosDeVisitante(@Param("idEquipo") Long idEquipo);


    Optional<Equipo> findByNombre(String nombre);
}
