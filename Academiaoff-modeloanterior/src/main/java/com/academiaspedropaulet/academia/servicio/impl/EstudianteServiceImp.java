package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.dtos.EstudianteDTO;
import com.academiaspedropaulet.academia.mappers.EstudianteMapper;
import com.academiaspedropaulet.academia.modelo.*;
import com.academiaspedropaulet.academia.repositorio.*;
import com.academiaspedropaulet.academia.servicio.EstudianteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class EstudianteServiceImp extends CrudGenericoServiceImp<Estudiante, Long> implements EstudianteService {
    private final EstudianteRepository kr;

    @Override
    protected ICrudGenericoRepository<Estudiante, Long> getRepo() { return kr; }
}
