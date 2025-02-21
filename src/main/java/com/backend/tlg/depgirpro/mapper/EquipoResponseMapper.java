package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.EquipoResponseDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EquipoResponseMapper {


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre")
    })
    EquipoResponseDTO toEquipoResponseDTO(Equipo equipo);




}
