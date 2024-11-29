package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.CompetenciaDTO;
import com.academiaspedropaulet.academia.mappers.CompetenciaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.CompetenciaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetenciaServiceImp extends CrudGenericoServiceImp<Competencia, Long> implements CompetenciaService {
    private final CompetenciaRepository repo;
    private final CompetenciaMapper apper;

    private final CursoRepository fk1;



    @Override
    protected ICrudGenericoRepository<Competencia, Long> getRepo() {
        return repo;
    }


    @Override
    public CompetenciaDTO saveD(CompetenciaDTO.CompetenciaCADto dto) {
        Competencia varkr = apper.toEntityFromCADTO(dto);

        Curso f1 = fk1.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setCurso(f1);

        Competencia varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public CompetenciaDTO updateD(CompetenciaDTO.CompetenciaCADto dto, Long id) {
        Competencia varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Competencia varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdCompetencia(varkr.getIdCompetencia());

        Curso f1 = fk1.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        
        varkrx.setCurso(f1);

        Competencia varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
