package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.MatriculaDTO;
import com.academiaspedropaulet.academia.mappers.MatriculaMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.MatriculaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MatriculaServiceImp extends CrudGenericoServiceImp<Matricula, Long> implements MatriculaService {
    private final MatriculaRepository repo;
    private final MatriculaMapper apper;

    private final EstudianteRepository fk1;
    private final ApoderadoRepository fk2;
    private final MedioRepository fk3;
    private final PlanRepository fk4;
    private final InstitucionRepository fk5;
    private final GrupoRepository fk6;


    @Override
    protected ICrudGenericoRepository<Matricula, Long> getRepo() {
        return repo;
    }


    @Override
    public MatriculaDTO saveD(MatriculaDTO.MatriculaCADto dto) {
        Matricula varkr = apper.toEntityFromCADTO(dto);

        Estudiante f1 = fk1.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Apoderado f2 = fk2.findById(dto.apoderado()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Medio f3 = fk3.findById(dto.medio()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Plan f4 = fk4.findById(dto.plan()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Institucion f5 = fk5.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontradoo"));
        Grupo f6 = fk6.findById(dto.grupo()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkr.setEstudiante(f1);
        varkr.setApoderado(f2);
        varkr.setMedio(f3);
        varkr.setPlan(f4);
        varkr.setInstitucion(f5);
        varkr.setGrupo(f6);

        Matricula varkrsv = repo.save(varkr);
        return apper.toDTO(varkrsv);
    }

    @Override
    public MatriculaDTO updateD(MatriculaDTO.MatriculaCADto dto, Long id) {
        Matricula varkr = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        Matricula varkrx = apper.toEntityFromCADTO(dto);
        varkrx.setIdMatricula(varkr.getIdMatricula());

        Estudiante f1 = fk1.findById(dto.estudiante()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Apoderado f2 = fk2.findById(dto.apoderado()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Medio f3 = fk3.findById(dto.medio()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Plan f4 = fk4.findById(dto.plan()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Institucion f5 = fk5.findById(dto.institucion()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));
        Grupo f6 = fk6.findById(dto.grupo()).orElseThrow(() -> new EntityNotFoundException("No encontrado"));

        varkrx.setEstudiante(f1);
        varkrx.setApoderado(f2);
        varkrx.setMedio(f3);
        varkrx.setPlan(f4);
        varkrx.setInstitucion(f5);
        varkrx.setGrupo(f6);


        Matricula varkrsvx = repo.save(varkrx);
        return apper.toDTO(varkrsvx);
    }
}
