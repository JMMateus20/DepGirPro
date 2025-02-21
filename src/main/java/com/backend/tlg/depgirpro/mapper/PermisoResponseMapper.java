package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.entity.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PermisoResponseMapper {

    @Mappings({
            @Mapping(source = "id", target = "idPermiso"),
            @Mapping(source = "rol.id", target = "idRol"),
            @Mapping(source = "rol.rol", target = "rol"),
            @Mapping(source = "operacion.id", target = "idOperacion"),
            @Mapping(source = "operacion.nombre", target = "operacion")
    })
    PermisoResponseDTO toPermisoResponseDTO(Permiso permiso);
}
