package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.TrabajadorDTO;
import com.academiaspedropaulet.academia.mappers.TrabajadorMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TrabajadorServiceImp extends CrudGenericoServiceImp<Trabajador, Long> implements TrabajadorService {
    private final TrabajadorRepository repo;
    private final TrabajadorMapper apper;

    private final InstitucionRepository fk1;


    @Override
    protected ICrudGenericoRepository<Trabajador, Long> getRepo() {
        return repo;
    }


    @Override
    public TrabajadorDTO saveD(TrabajadorDTO.TrabajadorCADto dto) {
        Trabajador varkr = apper.toEntityFromCADTO(dto);

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setInstitucion(f1);

        Trabajador varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public TrabajadorDTO updateD(TrabajadorDTO.TrabajadorCADto dto, Long id) {
        Trabajador varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Trabajador varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdTrabajador(varkr.getIdTrabajador());

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setInstitucion(f1);


        Trabajador varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
