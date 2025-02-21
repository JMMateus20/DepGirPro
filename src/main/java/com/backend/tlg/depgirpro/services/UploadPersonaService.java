package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import com.backend.tlg.depgirpro.dto.ImagenPersonaResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadPersonaService {

    ResponseEntity<?> upload(MultipartFile archivo, Long idPersona);

    ResponseEntity<ImagenPersonaResponseDTO> cambiarFoto(MultipartFile archivo, Long idPersona);

    ResponseEntity<Resource> verFoto(String nombreFoto);
}
