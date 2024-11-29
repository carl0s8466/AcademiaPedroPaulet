package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.NotaDTO;
import com.academiaspedropaulet.academia.mappers.NotaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.NotaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class NotaServiceImp extends CrudGenericoServiceImp<Nota, Long> implements NotaService {
    private final NotaRepository repo;
    private final NotaMapper apper;

    private final CompetenciaRepository fk1;


    @Override
    protected ICrudGenericoRepository<Nota, Long> getRepo() {
        return repo;
    }


    @Override
    public NotaDTO saveD(NotaDTO.NotaCADto dto) {
        Nota varkr = apper.toEntityFromCADTO(dto);

        Competencia f1 = fk1.findById(dto.competencia()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setCompetencia(f1);

        Nota varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public NotaDTO updateD(NotaDTO.NotaCADto dto, Long id) {
        Nota varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Nota varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdNota(varkr.getIdNota());

        Competencia f1 = fk1.findById(dto.competencia()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setCompetencia(f1);


        Nota varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
