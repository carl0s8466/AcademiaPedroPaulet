package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.LogroDTO;
import com.academiaspedropaulet.academia.mappers.LogroMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.LogroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LogroServiceImp extends CrudGenericoServiceImp<Logro, Long> implements LogroService {
    private final LogroRepository repo;
    private final LogroMapper apper;

    private final InstitucionRepository fk1;


    @Override
    protected ICrudGenericoRepository<Logro, Long> getRepo() {
        return repo;
    }


    @Override
    public LogroDTO saveD(LogroDTO.LogroCADto dto) {
        Logro varkr = apper.toEntityFromCADTO(dto);

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setInstitucion(f1);

        Logro varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public LogroDTO updateD(LogroDTO.LogroCADto dto, Long id) {
        Logro varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Logro varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdLogro(varkr.getIdLogro());

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setInstitucion(f1);


        Logro varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
