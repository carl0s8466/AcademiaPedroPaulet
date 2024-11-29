package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.CargaDTO;
import com.academiaspedropaulet.academia.mappers.CargaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.CargaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CargaServiceImp extends CrudGenericoServiceImp<Carga, Long> implements CargaService {
    private final CargaRepository repo;
    private final CargaMapper apper;

    private final PeriodoRepository fk1;
    private final TrabajadorRepository fk2;
    private final CursoRepository fk3;
    private final SeccionRepository fk4;


    @Override
    protected ICrudGenericoRepository<Carga, Long> getRepo() {
        return repo;
    }


    @Override
    public CargaDTO saveD(CargaDTO.CargaCADto dto) {
        Carga varkr = apper.toEntityFromCADTO(dto);

        Periodo f1 = fk1.findById(dto.periodo()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Trabajador f2 = fk2.findById(dto.trabajador()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Curso f3 = fk3.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Seccion f4 = fk4.findById(dto.seccion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkr.setPeriodo(f1);
        varkr.setTrabajador(f2);
        varkr.setCurso(f3);
        varkr.setSeccion(f4);

        Carga varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public CargaDTO updateD(CargaDTO.CargaCADto dto, Long id) {
        Carga varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Carga varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdCarga(varkr.getIdCarga());

        Periodo f1 = fk1.findById(dto.periodo()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Trabajador f2 = fk2.findById(dto.trabajador()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Curso f3 = fk3.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Seccion f4 = fk4.findById(dto.curso()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setPeriodo(f1);
        varkrx.setTrabajador(f2);
        varkrx.setCurso(f3);
        varkrx.setSeccion(f4);


        Carga varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}

