package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT new com.backend.tlg.depgirpro.dto.PermisoResponseDTO(p.id, r.id, r.rol, o.id, o.nombre) " +
    "FROM Rol r INNER JOIN r.permisos p INNER JOIN p.operacion o WHERE r.id=:idRol ORDER BY o.nombre ASC")
    List<PermisoResponseDTO> listarOperacionesPermitidas(@Param("idRol") Long idRol);

}
