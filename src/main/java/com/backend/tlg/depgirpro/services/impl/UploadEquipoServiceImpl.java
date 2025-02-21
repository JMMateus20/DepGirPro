package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.ImagenEquipoResponseDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import com.backend.tlg.depgirpro.entity.ImagenEquipo;
import com.backend.tlg.depgirpro.exceptions.InternalServerExceptionManaged;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.mapper.ImagenEquipoResponseMapper;
import com.backend.tlg.depgirpro.repository.EquipoRepository;
import com.backend.tlg.depgirpro.repository.ImagenRepository;
import com.backend.tlg.depgirpro.services.UploadEquipoService;
import com.backend.tlg.depgirpro.services.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class UploadEquipoServiceImpl implements UploadEquipoService {

    private final EquipoRepository equipoRep;
    private final ImagenRepository imagenRep;
    private final ImagenEquipoResponseMapper imagenEquipoResponseMapper;
    private final UploadService uploadService;

    private final static String DIR="equipos";
    private final static String URL="http://localhost:8080/images/";


    @Override
    public ResponseEntity<?> upload(MultipartFile archivo, Long idEquipo) {
        Map<String, Object> response=new HashMap<>();
        Equipo equipoBD=this.equipoRep.findById(idEquipo).orElseThrow(
                ()->new NotFoundExceptionManaged("404",
                        "Error de búsqueda",
                        "Equipo no encontrado en la base de datos",
                        HttpStatus.NOT_FOUND));
        String nombreArchivo=this.uploadService.cargarImagen(archivo, DIR);
        equipoBD.getImagenes().add(new ImagenEquipo(URL.concat(nombreArchivo), equipoBD));
        this.equipoRep.save(equipoBD);
        response.put("imagen", this.imagenEquipoResponseMapper.toImagenEquipoResponseDTOList(equipoBD.getImagenes()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Override
    public ResponseEntity<ImagenEquipoResponseDTO> cambiarFoto(MultipartFile archivo, Long idImagen) {
        ImagenEquipo imagenEquipoBD =this.imagenRep.findById(idImagen).orElseThrow(
                ()->new NotFoundExceptionManaged("404",
                        "Error de búsqueda",
                        "No se ha encontrado la imagen",
                        HttpStatus.NOT_FOUND));
        String nombreArchivo= imagenEquipoBD.getUrl().split("/")[4];
        System.out.println(nombreArchivo);
        this.uploadService.eliminarImagen(nombreArchivo, DIR);
        String nombreArchivoNuevo=this.uploadService.cargarImagen(archivo, DIR);
        imagenEquipoBD.setUrl(URL.concat(nombreArchivoNuevo));
        this.imagenRep.save(imagenEquipoBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.imagenEquipoResponseMapper.toImagenEquipoResponseDTO(imagenEquipoBD));
    }

    @Override
    public ResponseEntity<Resource> verFoto(String nombreFoto) {
        return this.uploadService.verFoto(nombreFoto, DIR);
    }



}
