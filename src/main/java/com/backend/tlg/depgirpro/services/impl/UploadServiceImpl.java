package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.exceptions.IOExceptionManaged;
import com.backend.tlg.depgirpro.exceptions.InternalServerExceptionManaged;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.services.UploadService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private static final String RUTA_ARCHIVOS="uploads/";

    @Override
    public String cargarImagen(MultipartFile archivo, String dir) {
        String nombreArchivo=null;
        if (!archivo.isEmpty()) {
            nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get(RUTA_ARCHIVOS.concat(dir)).resolve(nombreArchivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                throw new IOExceptionManaged("500", "Error del servidor", "No se pudo cargar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return nombreArchivo;
    }

    @Override
    public void eliminarImagen(String nombreArchivo, String dir) {
        if (nombreArchivo!=null && nombreArchivo.length()>0){
            Path rutaFotoAntigua=Paths.get(RUTA_ARCHIVOS.concat(dir)).resolve(nombreArchivo).toAbsolutePath();
            File archivoFotoAntigua=rutaFotoAntigua.toFile();
            if (archivoFotoAntigua.exists() && archivoFotoAntigua.canRead()){
                archivoFotoAntigua.delete();
            }else{
                throw new NotFoundExceptionManaged("404", "Error de búsqueda", "El archivo de imagen no se puede leer o no existe", HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public ResponseEntity<Resource> verFoto(String nombreFoto, String dir) {
        Path rutaArchivo=Paths.get(RUTA_ARCHIVOS.concat(dir)).resolve(nombreFoto).toAbsolutePath();
        Resource recurso=null;
        try{
            recurso=new UrlResource(rutaArchivo.toUri());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()){
            throw new InternalServerExceptionManaged("No se pudo cargar la imagen");
        }
        HttpHeaders cabecera=new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
