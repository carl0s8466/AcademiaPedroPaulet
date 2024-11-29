package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.AsistenciaDTO;
import com.academiaspedropaulet.academia.mappers.AsistenciaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.AsistenciaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AsistenciaServiceImp extends CrudGenericoServiceImp<Asistencia, Long> implements AsistenciaService {
    private final AsistenciaRepository repo;
    private final AsistenciaMapper apper;

    private final EstadotenciaRepository fk1;
    private final PeriodoRepository fk2;
    private final EstudianteRepository fk3;
    private final CursoRepository fk4;


    @Override
    protected ICrudGenericoRepository<Asistencia, Long> getRepo() {
        return repo;
    }


    @Override
    public AsistenciaDTO saveD(AsistenciaDTO.AsistenciaCADto dto) {
        Asistencia varkr = apper.toEntityFromCADTO(dto);

        Estadotencia f1 = fk1.findById(dto.estadotencia()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Periodo f2 = fk2.findById(dto.periodo()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Estudiante f3 = fk3.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Curso f4 = fk4.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkr.setEstadotencia(f1);
        varkr.setPeriodo(f2);
        varkr.setEstudiante(f3);
        varkr.setCurso(f4);

        Asistencia varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public AsistenciaDTO updateD(AsistenciaDTO.AsistenciaCADto dto, Long id) {
        Asistencia varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Asistencia varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdAsistencia(varkr.getIdAsistencia());

        Estadotencia f1 = fk1.findById(dto.estadotencia()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Periodo f2 = fk2.findById(dto.periodo()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Estudiante f3 = fk3.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Curso f4 = fk4.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setEstadotencia(f1);
        varkrx.setPeriodo(f2);
        varkrx.setEstudiante(f3);
        varkrx.setCurso(f4);


        Asistencia varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
