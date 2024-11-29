package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.ComunicadoDTO;
import com.academiaspedropaulet.academia.mappers.ComunicadoMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.ComunicadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ComunicadoServiceImp extends CrudGenericoServiceImp<Comunicado, Long> implements ComunicadoService {
    private final ComunicadoRepository repo;
    private final ComunicadoMapper apper;

    private final InstitucionRepository fk1;



    @Override
    protected ICrudGenericoRepository<Comunicado, Long> getRepo() {
        return repo;
    }


    @Override
    public ComunicadoDTO saveD(ComunicadoDTO.ComunicadoCADto dto) {
        Comunicado varkr = apper.toEntityFromCADTO(dto);

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        
        varkr.setInstitucion(f1);

        Comunicado varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public ComunicadoDTO updateD(ComunicadoDTO.ComunicadoCADto dto, Long id) {
        Comunicado varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Comunicado varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdComunicado(varkr.getIdComunicado());

        Institucion f1 = fk1.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setInstitucion(f1);

        Comunicado varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
