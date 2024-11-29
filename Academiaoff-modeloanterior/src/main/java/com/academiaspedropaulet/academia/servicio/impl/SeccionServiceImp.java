package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.SeccionDTO;
import com.academiaspedropaulet.academia.mappers.SeccionMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.SeccionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SeccionServiceImp extends CrudGenericoServiceImp<Seccion, Long> implements SeccionService {
    private final SeccionRepository repo;
    private final SeccionMapper apper;

    private final NivelRepository fk1;


    @Override
    protected ICrudGenericoRepository<Seccion, Long> getRepo() {
        return repo;
    }


    @Override
    public SeccionDTO saveD(SeccionDTO.SeccionCADto dto) {
        Seccion varkr = apper.toEntityFromCADTO(dto);

        Nivel f1 = fk1.findById(dto.nivel()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setNivel(f1);

        Seccion varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public SeccionDTO updateD(SeccionDTO.SeccionCADto dto, Long id) {
        Seccion varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Seccion varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdSeccion(varkr.getIdSeccion());

        Nivel f1 = fk1.findById(dto.nivel()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setNivel(f1);


        Seccion varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
