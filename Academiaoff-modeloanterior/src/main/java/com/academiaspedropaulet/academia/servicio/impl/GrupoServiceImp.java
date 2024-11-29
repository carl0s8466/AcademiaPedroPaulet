package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.GrupoDTO;
import com.academiaspedropaulet.academia.mappers.GrupoMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.GrupoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GrupoServiceImp extends CrudGenericoServiceImp<Grupo, Long> implements GrupoService {
    private final GrupoRepository repo;
    private final GrupoMapper apper;

    private final SeccionRepository fk1;


    @Override
    protected ICrudGenericoRepository<Grupo, Long> getRepo() {
        return repo;
    }


    @Override
    public GrupoDTO saveD(GrupoDTO.GrupoCADto dto) {
        Grupo varkr = apper.toEntityFromCADTO(dto);

        Seccion f1 = fk1.findById(dto.seccion()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setSeccion(f1);

        Grupo varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public GrupoDTO updateD(GrupoDTO.GrupoCADto dto, Long id) {
        Grupo varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Grupo varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdGrupo(varkr.getIdGrupo());

        Seccion f1 = fk1.findById(dto.seccion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setSeccion(f1);


        Grupo varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
