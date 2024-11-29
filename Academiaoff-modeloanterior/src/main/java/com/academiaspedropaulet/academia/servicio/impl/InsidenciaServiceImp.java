package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.InsidenciaDTO;
import com.academiaspedropaulet.academia.mappers.InsidenciaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.InsidenciaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InsidenciaServiceImp extends CrudGenericoServiceImp<Insidencia, Long> implements InsidenciaService {
    private final InsidenciaRepository repo;
    private final InsidenciaMapper apper;

    private final CursoRepository fk1;
    private final EstudianteRepository fk2;


    @Override
    protected ICrudGenericoRepository<Insidencia, Long> getRepo() {
        return repo;
    }


    @Override
    public InsidenciaDTO saveD(InsidenciaDTO.InsidenciaCADto dto) {
        Insidencia varkr = apper.toEntityFromCADTO(dto);

        Curso f1 = fk1.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Estudiante f2 = fk2.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkr.setCurso(f1);
        varkr.setEstudiante(f2);

        Insidencia varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public InsidenciaDTO updateD(InsidenciaDTO.InsidenciaCADto dto, Long id) {
        Insidencia varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Insidencia varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdInsidencia(varkr.getIdInsidencia());

        Curso f1 = fk1.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Estudiante f2 = fk2.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setCurso(f1);
        varkrx.setEstudiante(f2);


        Insidencia varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
