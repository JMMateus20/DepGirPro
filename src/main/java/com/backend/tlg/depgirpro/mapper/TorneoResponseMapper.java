package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.TorneoResponseDTO;
import com.backend.tlg.depgirpro.entity.Torneo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TorneoResponseMapper {


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fechaInicial.fecha", target = "fechaInicial"),
            @Mapping(source = "fechaFinal.fecha", target = "fechaFinal")
    })
    TorneoResponseDTO toTorneoResponseDTO(Torneo torneo);
}
