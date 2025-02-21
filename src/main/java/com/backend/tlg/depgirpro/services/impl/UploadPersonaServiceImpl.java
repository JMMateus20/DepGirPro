package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import com.backend.tlg.depgirpro.dto.ImagenPersonaResponseDTO;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.mapper.ImagenPersonaResponseMapper;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import com.backend.tlg.depgirpro.services.UploadPersonaService;
import com.backend.tlg.depgirpro.services.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class UploadPersonaServiceImpl implements UploadPersonaService {

    private final PersonaRepository personaRep;
    private final UploadService uploadService;
    private final ImagenPersonaResponseMapper mapper;

    private final static String DIR="personas";
    private final static String URL="http://localhost:8080/images/personas/";
    @Override
    public ResponseEntity<?> upload(MultipartFile archivo, Long idPersona) {
        Map<String, Object> respuesta=new HashMap<>();
        Persona personaBD=this.personaRep.findById(idPersona).orElseThrow(
                ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "No se encontró la persona en la base de datos", HttpStatus.NOT_FOUND)
        );
        String nombreArchivo=this.uploadService.cargarImagen(archivo, DIR);
        personaBD.setUrlFoto(URL.concat(nombreArchivo));
        this.personaRep.save(personaBD);
        respuesta.put("imagen", this.mapper.toImagenPersonaResponseDTO(personaBD));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<ImagenPersonaResponseDTO> cambiarFoto(MultipartFile archivo, Long idPersona) {
        Persona personaBD=this.personaRep.findById(idPersona).orElseThrow(
                ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "No se encontró la persona en la base de datos", HttpStatus.NOT_FOUND)
        );
        String nombreFoto=personaBD.getUrlFoto().split("/")[5];
        this.uploadService.eliminarImagen(nombreFoto, DIR);
        String nombreFotoNueva=this.uploadService.cargarImagen(archivo, DIR);
        personaBD.setUrlFoto(URL.concat(nombreFotoNueva));
        this.personaRep.save(personaBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toImagenPersonaResponseDTO(personaBD));
    }

    @Override
    public ResponseEntity<Resource> verFoto(String nombreFoto) {
        return this.uploadService.verFoto(nombreFoto, DIR);

    }
}
