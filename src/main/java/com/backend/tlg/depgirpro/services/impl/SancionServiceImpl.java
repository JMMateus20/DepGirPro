package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.RegistroSancionDTO;
import com.backend.tlg.depgirpro.dto.SancionResponseDTO;
import com.backend.tlg.depgirpro.entity.Multa;
import com.backend.tlg.depgirpro.entity.Sancion;
import com.backend.tlg.depgirpro.mapper.MultaRequestMapper;
import com.backend.tlg.depgirpro.mapper.SancionResponseMapper;
import com.backend.tlg.depgirpro.repository.SancionRepository;
import com.backend.tlg.depgirpro.services.SancionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class SancionServiceImpl implements SancionService {

    private final SancionRepository sancionRep;
    private final MultaRequestMapper multaRequestMapper;
    private final SancionResponseMapper sancionResponseMapper;


    @Override
    public ResponseEntity<?> insertar(RegistroSancionDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();
        Sancion sancionNew=this.sancionRep.save(new Sancion(dto.getSancion(), this.multaRequestMapper.toMulta(dto)));
        respuesta.put("sancion", this.sancionResponseMapper.toSancionResponseDTO(sancionNew));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
