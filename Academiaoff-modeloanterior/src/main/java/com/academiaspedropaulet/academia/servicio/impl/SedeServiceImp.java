package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Sede;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.SedeRepository;
import com.academiaspedropaulet.academia.servicio.SedeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SedeServiceImp extends CrudGenericoServiceImp<Sede, Long> implements SedeService {
    private final SedeRepository kr;

    @Override
    protected ICrudGenericoRepository<Sede, Long> getRepo() { return kr; }

}
