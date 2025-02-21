package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.SancionResponseDTO;
import com.backend.tlg.depgirpro.entity.Sancion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface SancionResponseMapper {


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "sancion", target = "sancion"),
            @Mapping(source = "multa.multa", target = "multa")
    })
    SancionResponseDTO toSancionResponseDTO(Sancion sancion);
}
