package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.RegistroSancionDTO;
import com.backend.tlg.depgirpro.entity.Multa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MultaRequestMapper {

    @Mappings({
            @Mapping(source = "multaDTO.multa", target = "multa")
    })
    Multa toMulta(RegistroSancionDTO dto);

}
