package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.ImagenPersonaResponseDTO;
import com.backend.tlg.depgirpro.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ImagenPersonaResponseMapper {

    @Mappings({
            @Mapping(source = "id", target = "idPersona"),
            @Mapping(source = "urlFoto", target = "urlImagen")
    })
    ImagenPersonaResponseDTO toImagenPersonaResponseDTO(Persona persona);


}
