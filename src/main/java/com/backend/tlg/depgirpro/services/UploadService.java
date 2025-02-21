package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {


    String cargarImagen(MultipartFile archivo, String dir);


    void eliminarImagen(String nombreArchivo, String dir);


    ResponseEntity<Resource> verFoto(String nombreFoto, String dir);
}
