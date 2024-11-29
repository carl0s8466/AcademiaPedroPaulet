package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.PlanDTO;
import com.academiaspedropaulet.academia.mappers.PlanMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.PlanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImp extends CrudGenericoServiceImp<Plan, Long> implements PlanService {
    private final PlanRepository repo;
    private final PlanMapper apper;

    private final CargaRepository fk1;


    @Override
    protected ICrudGenericoRepository<Plan, Long> getRepo() {
        return repo;
    }


    @Override
    public PlanDTO saveD(PlanDTO.PlanCADto dto) {
        Plan varkr = apper.toEntityFromCADTO(dto);

        Carga f1 = fk1.findById(dto.carga()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));

        varkr.setCarga(f1);

        Plan varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public PlanDTO updateD(PlanDTO.PlanCADto dto, Long id) {
        Plan varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Plan varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdPlan(varkr.getIdPlan());

        Carga f1 = fk1.findById(dto.carga()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setCarga(f1);


        Plan varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
