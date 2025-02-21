package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.EncuentroResponseDTO;
import com.backend.tlg.depgirpro.dto.RegistroEncuentroDTO;
import com.backend.tlg.depgirpro.dto.RegistroTorneoDTO;
import com.backend.tlg.depgirpro.dto.TorneoResponseDTO;
import com.backend.tlg.depgirpro.entity.Encuentro;
import com.backend.tlg.depgirpro.entity.Equipo;
import com.backend.tlg.depgirpro.entity.Fecha;
import com.backend.tlg.depgirpro.entity.Torneo;
import com.backend.tlg.depgirpro.exceptions.BussinessRuleException;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.mapper.EncuentroResponseMapper;
import com.backend.tlg.depgirpro.mapper.TorneoResponseMapper;
import com.backend.tlg.depgirpro.repository.EquipoRepository;
import com.backend.tlg.depgirpro.repository.FechaRepository;
import com.backend.tlg.depgirpro.repository.TorneoRepository;
import com.backend.tlg.depgirpro.services.TorneoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TorneoServiceImpl implements TorneoService {

    private final TorneoRepository torneoRep;
    private final EquipoRepository equipoRep;
    private final FechaRepository fechaRep;
    private final TorneoResponseMapper torneoResponseMapper;
    private final EncuentroResponseMapper encuentroResponseMapper;
    @Override
    public ResponseEntity<?> insertar(RegistroTorneoDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();
        if ((dto.getFechaInicial().getFecha().compareTo(dto.getFechaFinal().getFecha()))>0){
            return ResponseEntity.badRequest().body("La fecha inicial debe ser anterior a la fecha final");
        }
        Calendar fechaInicial=Calendar.getInstance();
        fechaInicial.setTime(dto.getFechaInicial().getFecha());
        Calendar fechaFinal=Calendar.getInstance();
        fechaFinal.setTime(dto.getFechaFinal().getFecha());
        Optional<Fecha> fechaInicialBD=this.fechaRep.findByAnioAndMesAndDia(fechaInicial.get(Calendar.YEAR), fechaInicial.get(Calendar.MONTH)+1, fechaInicial.get(Calendar.DAY_OF_MONTH));
        Optional<Fecha> fechaFinalBD=this.fechaRep.findByAnioAndMesAndDia(fechaFinal.get(Calendar.YEAR), fechaFinal.get(Calendar.MONTH)+1, fechaFinal.get(Calendar.DAY_OF_MONTH));
        Fecha fechaInicialTorneo=fechaInicialBD.orElse(new Fecha(dto.getFechaInicial().getFecha(),fechaInicial.get(Calendar.YEAR), fechaInicial.get(Calendar.MONTH)+1, fechaInicial.get(Calendar.DAY_OF_MONTH))); //si la fecha ya existe en la base de datos, la asigna a la variable, sino, en el orElse, se crea un nuevo objeto de tipo Fecha para guardarla en la base de datos.
        Fecha fechaFinalTorneo= fechaFinalBD.orElse(new Fecha(dto.getFechaFinal().getFecha(), fechaFinal.get(Calendar.YEAR), fechaFinal.get(Calendar.MONTH)+1, fechaFinal.get(Calendar.DAY_OF_MONTH)));
        Torneo torneoNew=this.torneoRep.save(new Torneo(fechaInicialTorneo, fechaFinalTorneo));
        respuesta.put("torneo", this.torneoResponseMapper.toTorneoResponseDTO(torneoNew));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<?> agregarEncuentro(Long idTorneo, RegistroEncuentroDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();


        Equipo equipoLocalBD=this.equipoRep.findById(dto.getIdEquipoLocal()).orElseThrow(()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Equipo no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        Equipo equipoVisitanteBD=this.equipoRep.findById(dto.getIdEquipoVisitante()).orElseThrow(()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Equipo no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        Torneo torneoBD=this.torneoRep.findById(idTorneo).orElseThrow(()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Torneo no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dto.getFecha().getFecha());
        Optional<Fecha> fechaBD=this.fechaRep.findByAnioAndMesAndDia(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
        Fecha fechaEncuentro=fechaBD.orElse(new Fecha(dto.getFecha().getFecha(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
        Encuentro encuentroNew=new Encuentro(equipoLocalBD, equipoVisitanteBD, fechaEncuentro, torneoBD, dto.getJornada());
        torneoBD.getEncuentros().add(encuentroNew);
        this.torneoRep.save(torneoBD);
        respuesta.put("encuentro", this.encuentroResponseMapper.toEncuentroResponseDTO(encuentroNew));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
