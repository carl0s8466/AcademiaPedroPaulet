package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.InstitucionDTO;
import com.academiaspedropaulet.academia.mappers.InstitucionMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.InstitucionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitucionServiceImp extends CrudGenericoServiceImp<Institucion, Long> implements InstitucionService {
    private final InstitucionRepository repo;
    private final InstitucionMapper apper;

    private final SedeRepository fk1;


    @Override
    protected ICrudGenericoRepository<Institucion, Long> getRepo() {
        return repo;
    }


    @Override
    public InstitucionDTO saveD(InstitucionDTO.InstitucionCADto dto) {
        Institucion varkr = apper.toEntityFromCADTO(dto);

        Sede f1 = fk1.findById(dto.sede()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setSede(f1);

        Institucion varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public InstitucionDTO updateD(InstitucionDTO.InstitucionCADto dto, Long id) {
        Institucion varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Institucion varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdInstitucion(varkr.getIdInstitucion());

        Sede f1 = fk1.findById(dto.sede()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setSede(f1);


        Institucion varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
