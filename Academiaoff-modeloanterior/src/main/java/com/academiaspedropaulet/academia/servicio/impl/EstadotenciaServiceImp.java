package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Estadotencia;
import com.academiaspedropaulet.academia.repositorio.EstadotenciaRepository;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.servicio.EstadotenciaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class EstadotenciaServiceImp extends CrudGenericoServiceImp<Estadotencia, Long> implements EstadotenciaService {
    private final EstadotenciaRepository kr;

    @Override
    protected ICrudGenericoRepository<Estadotencia, Long> getRepo() { return kr; }

}
