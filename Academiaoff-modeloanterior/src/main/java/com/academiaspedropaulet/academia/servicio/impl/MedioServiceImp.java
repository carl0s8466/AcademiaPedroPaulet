package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Medio;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.MedioRepository;
import com.academiaspedropaulet.academia.servicio.MedioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MedioServiceImp extends CrudGenericoServiceImp<Medio, Long> implements MedioService {
    private final MedioRepository kr;

    @Override
    protected ICrudGenericoRepository<Medio, Long> getRepo() { return kr; }

}
