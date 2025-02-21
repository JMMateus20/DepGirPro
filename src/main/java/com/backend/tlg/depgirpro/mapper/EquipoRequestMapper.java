package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.RegistroEquipoDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EquipoRequestMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "cantidad_jugadores", target = "cantidad_jugadores")
    })
    Equipo toEquipo(RegistroEquipoDTO dto);
}
