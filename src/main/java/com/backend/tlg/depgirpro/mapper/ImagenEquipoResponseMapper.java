package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import com.backend.tlg.depgirpro.entity.ImagenEquipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenEquipoResponseMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "equipo.nombre", target = "nombreEquipo")
    })
    ImagenEquipoResponseDTO toImagenEquipoResponseDTO(ImagenEquipo imagenEquipo);



    List<ImagenEquipoResponseDTO> toImagenEquipoResponseDTOList(List<ImagenEquipo> imagenes);
}
