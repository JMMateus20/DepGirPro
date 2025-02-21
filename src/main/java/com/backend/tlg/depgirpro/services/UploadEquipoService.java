package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadEquipoService {

    ResponseEntity<?> upload(MultipartFile archivo, Long idEquipo);

    ResponseEntity<ImagenEquipoResponseDTO> cambiarFoto(MultipartFile archivo, Long idImagen);

    ResponseEntity<Resource> verFoto(String nombreFoto);
}
