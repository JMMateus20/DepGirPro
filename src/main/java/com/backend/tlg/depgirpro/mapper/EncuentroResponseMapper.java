package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.EncuentroResponseDTO;
import com.backend.tlg.depgirpro.entity.Encuentro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EncuentroResponseMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "equipoLocal.nombre", target = "nomEquipoLocal"),
            @Mapping(source = "equipoVisitante.nombre", target = "nomEquipoVisitante"),
            @Mapping(source = "fecha.fecha", target = "fecha"),
            @Mapping(source = "jornada", target = "jornada")
    })
    EncuentroResponseDTO toEncuentroResponseDTO(Encuentro encuentro);
}
